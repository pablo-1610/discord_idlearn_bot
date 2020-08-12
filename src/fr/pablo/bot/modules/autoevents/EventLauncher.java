package fr.pablo.bot.modules.autoevents;

import fr.pablo.bot.Main;
import fr.pablo.bot.data.cache.CacheSystem;
import fr.pablo.bot.modules.autoevents.objects.EventQuestion;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class EventLauncher {
    public static EventQuestion actualQuestion = null;
    public static List<EventQuestion> luaQuestions = new ArrayList<>();
    public static long eventChannelID = 0;
    public static String winner = "nil";
    private static DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public static Date eventLaunchedAt;

    public static void initializeQuestions(){
        luaQuestions.add(new EventQuestion("Quelle méthode permet de déclencher l'execution de code après un temps imparti? (On ne parle pas du Wait!)",Arrays.asList("Citizen.SetTimeout","SetTimeout")));
        luaQuestions.add(new EventQuestion("Comment déclare-t-on une fonction accessible uniquement dans le fichier où elle est déclarée?",Arrays.asList("local function")));
        luaQuestions.add(new EventQuestion("Définissez la variable myPed en tant qu'entité invincible.",Arrays.asList("SetEntityInvincible(myPed,true)", "SetEntityInvincible(myPed, true)")));
        luaQuestions.add(new EventQuestion("Obtenez les coordonnées (vecteur3) de l'entité de variable myPed",Arrays.asList("GetEntityCoords(myPed)", "local vector3 = GetEntityCoords(myPed)","vector3 = GetEntityCoords(myPed)")));
        luaQuestions.add(new EventQuestion("Chargez le modèle blista dans le cache du client (sans pré-attribution d'une variable pour le modèle).",Arrays.asList("RequestModel(GetHashKey('blista')","RequestModel(GetHashKey(\"blista\")")));
    }

    public static void launch(){
        Random random = new Random();
        actualQuestion = luaQuestions.get(random.nextInt(luaQuestions.size()));
        Calendar ca = Calendar.getInstance();
        Date date = new Date();

        ca.setTime(date);
        ca.add(Calendar.HOUR_OF_DAY, 2);

        eventLaunchedAt = ca.getTime();;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, 2);
        calendar.add(Calendar.MINUTE, 10);
        Date end = calendar.getTime();

        Main.jda.getGuildById(741640428335661071L).getCategoryById(743133876532019200L).createTextChannel("│\uD83D\uDD14│・\uD835\uDDA2hallenge").queue(textChannel -> {
            eventChannelID = textChannel.getIdLong();
            textChannel.putPermissionOverride(Main.jda.getGuildById(741640428335661071L).getRoleById(741698297890209842L)).setDeny(Permission.VIEW_CHANNEL).queue();
            textChannel.putPermissionOverride(Main.jda.getGuildById(741640428335661071L).getRoleById(742422480785899592L)).setAllow(Permission.MESSAGE_WRITE,Permission.VIEW_CHANNEL).queue();
            textChannel.sendMessage(Main.jda.getGuildById(741640428335661071L).getRoleById(742422480785899592L).getAsMention() + "\n\n**Challenge Lua (FiveM)**\n\n> " + actualQuestion.getQuestion() + "\n\nVous avez **10 minutes** (Fin: " + format.format(end) + ") pour répondre à la question. Si vous vous trompez, le salon vous sera vérouillé, sinon, vous ferez gagner **2** points à votre Équipe!\n\nMarquez ci-dessous votre réponse. Bonne chance!").queueAfter(5, TimeUnit.SECONDS);
        });
    }

    public static void awnser(MessageReceivedEvent e){
        String msg = e.getMessage().getContentRaw().toLowerCase();
        e.getMessage().delete().queue();
        if(winner.equals("nil")) {
            actualQuestion.getCorrectAwnser().forEach(awnser -> {
                if (winner.equals("nil")) {
                    if (msg.startsWith(awnser.toLowerCase())) {
                        e.getTextChannel().putPermissionOverride(Main.jda.getGuildById(741640428335661071L).getRoleById(742422480785899592L)).setAllow(Permission.VIEW_CHANNEL).setDeny(Permission.MESSAGE_WRITE).queue();
                        winner = e.getAuthor().getAsTag();

                        Integer teamID = CacheSystem.usersCache.get(e.getAuthor().getId()).getTeam();
                        Main.jda.getTextChannelById(CacheSystem.teamDataCache.get(teamID).getAnnounceChannelID()).sendMessage(e.getAuthor().getAsMention() + " vous fait remporter **2** points grâce à sa bonne réponse lors du challenge!").queue();
                        CacheSystem.teamCache.get(teamID).setPts(CacheSystem.teamCache.get(teamID).getPts() + 2);

                        e.getChannel().sendMessage(e.getAuthor().getAsMention() + " remporte le challenge! Suppression du salon dans quelques minutes..").queue();

                        return;
                    }
                }
            });
            e.getTextChannel().putPermissionOverride(e.getMember()).setAllow(Permission.VIEW_CHANNEL).setDeny(Permission.MESSAGE_WRITE).queue();
        }
    }

    public static class Loop extends TimerTask {
        private int running = 0;
        @Override
        public void run() {
            if(running == 0){
                launch();
                running = 1;
            } else {
                actualQuestion = null;
                Main.jda.getGuildById(741640428335661071L).getTextChannelById(eventChannelID).delete().queue();

                if (winner == "nil"){
                    Main.jda.getTextChannelById(743133754045759540L).sendMessage("Le challenge [Lancé le: " + format.format(eventLaunchedAt) + "] s'est terminé sans gagnant :( !").queue();

                } else {
                    Main.jda.getTextChannelById(743133754045759540L).sendMessage("Le challenge [Lancé le: " + format.format(eventLaunchedAt) + "] a été remporté par: **" + winner + "** !").queue();
                }
                winner = "nil";
                running = 0;
            }
        }
    }
}

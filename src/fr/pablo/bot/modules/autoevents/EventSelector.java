package fr.pablo.bot.modules.autoevents;

import fr.pablo.bot.Main;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;

public class EventSelector {
    public static long messageID;
    public static String reactionID_luaFiveMChallenges = "\uD83D\uDCBD";
    public static void initialize(){

        Main.jda.getTextChannelById(743127101359521802L).deleteMessageById(Main.jda.getTextChannelById(743127101359521802L).getLatestMessageId()).queue();

        Main.jda.getTextChannelById(743127101359521802L).sendMessage("Challenges des développeurs\n\n__Qu'est-ce que c'est ?__:\n\nLes challenges développeurs sont des challenges qui apparaitront chaques 20 minutes avec une question aléatoire sur les thèmes que vous avez sélectionnés.\n\n**Selections possibles**:\n  • "+reactionID_luaFiveMChallenges+": Challenges LUA (FiveM)\n\nRéagissez avec les emojis ci-dessous pour sélectionner les challenges qui vous intéressent. Des catégories de challenges s'ajouteront au fil du temps").queue(message -> {
            messageID = message.getIdLong();
            message.addReaction(reactionID_luaFiveMChallenges).queue();
        });
    }

    public static void reactAdd(MessageReactionAddEvent e){
        if (e.getReaction().getReactionEmote().getEmoji().equals(reactionID_luaFiveMChallenges)){
            // Challenges lua
            e.getGuild().addRoleToMember(e.getUserId(), e.getGuild().getRoleById(742422480785899592L)).queue();
        }
    }


    public static void reactRmv(MessageReactionRemoveEvent e){
        if (e.getReaction().getReactionEmote().getEmoji().equals(reactionID_luaFiveMChallenges)){
            // Challenges lua
            e.getGuild().removeRoleFromMember(e.getUserId(), e.getGuild().getRoleById(742422480785899592L)).queue();
        }
    }
}

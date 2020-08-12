package fr.pablo.bot.trigger.list;

import fr.pablo.bot.Main;
import fr.pablo.bot.data.cache.CacheSystem;
import fr.pablo.bot.data.cache.cache_objects.IDLearner;
import fr.pablo.bot.data.database.DbConnection;
import fr.pablo.bot.modules.autoevents.EventSelector;
import fr.pablo.bot.teams.Team;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;

public class ReactionAdd {
    public ReactionAdd(MessageReactionAddEvent e) {
        if (e.getMember().getUser().equals(e.getJDA().getSelfUser())) return;

        if(e.getChannel().getId().equals(String.valueOf(743127101359521802L))){
            EventSelector.reactAdd(e);
            return;
        }

        if(!e.getChannel().getId().equals(String.valueOf(742329577258483732L))) return;

        DbConnection userConnection = Main.getDbManager().getUserConnection();
        try {
            Connection connection = userConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            preparedStatement.setString(1, e.getUserId());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                System.out.println("[Debug] " + e.getUser().getName() + " want to create account ! (ALREADY IN DB)");
                e.getUser().openPrivateChannel().queue((channel) ->
                {
                    channel.sendMessage("Vous avez déjà un compte enregistré, merci de contacter un administrateur.").queue();
                });
                return;
            }

            Random random = new Random();
            List<Team> avaibleTeams = Arrays.asList(Team.values());
            int rTeam = random.nextInt(avaibleTeams.size());
            Team team = avaibleTeams.get(rTeam);

            Connection connectiona = userConnection.getConnection();
            PreparedStatement preparedStatemena = connectiona.prepareStatement("INSERT INTO `users`(`id`, `name`, `team`) VALUES (?,?,?)");
            preparedStatemena.setString(1, e.getUserId());
            preparedStatemena.setString(2, e.getUser().getName());
            preparedStatemena.setInt(3, rTeam);
            preparedStatemena.executeUpdate();
            CacheSystem.usersCache.put(e.getUserId(), new IDLearner(e.getUserId(),e.getUser().getName(),0,0,500,0,0,0,0,rTeam));
            e.getGuild().addRoleToMember(e.getUserId(), e.getGuild().getRoleById(741698297890209842L)).queue();
            e.getGuild().addRoleToMember(e.getUserId(), e.getGuild().getRoleById(741698158958215279L)).queue();
            e.getGuild().addRoleToMember(e.getUserId(), e.getGuild().getRoleById(team.getRoleID())).queue();
            e.getGuild().getTextChannelById(team.getChatChannelID()).sendMessage(e.getUser().getAsMention() + " vient de rejoindre votre équipe " + team.getPrefix() + " !").queue();
            e.getGuild().getTextChannelById(742356893229645834L).sendMessage(e.getUser().getAsMention() + " [" + e.getUser().getAsTag() + "] vient de rejoindre " + team.getPrefix()).queue();
            System.out.println("[Debug] " + e.getUser().getName() + " account created");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }
}
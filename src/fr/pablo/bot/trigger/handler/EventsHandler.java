package fr.pablo.bot.trigger.handler;

import fr.pablo.bot.Main;
import fr.pablo.bot.data.database.DbConnection;
import fr.pablo.bot.trigger.list.*;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.EventListener;

import javax.annotation.Nonnull;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventsHandler implements EventListener {
    public static int i =0;
    @Override
    public void onEvent(@Nonnull GenericEvent e) {
        if(e instanceof MessageReceivedEvent) new MsgEvent((MessageReceivedEvent) e);
        if(e instanceof MessageReactionAddEvent) new ReactionAdd((MessageReactionAddEvent) e);
        if(e instanceof ReadyEvent) new Ready(e);
        if(e instanceof GuildMemberJoinEvent) new Join((GuildMemberJoinEvent) e);
        if(e instanceof GuildMemberLeaveEvent) new Quit((GuildMemberLeaveEvent) e);
        if(e instanceof MessageReactionRemoveEvent) new ReactionRemove((MessageReactionRemoveEvent) e);
    }

    private void onMRAE(MessageReactionAddEvent e) {
        if(e.getMember().getUser().equals(e.getJDA().getSelfUser())) return;
        if(!Main.userAndMessage.containsKey(e.getMember().getIdLong())) return;
        if(Main.userAndMessage.get(e.getMember().getIdLong()).equals(e.getMessageIdLong())) {
            if (e.getReaction().getReactionEmote().getEmoji().equals("☎")) {
                e.getChannel().deleteMessageById(Main.userAndMessage.get(e.getMember().getIdLong())).queue();
                e.getGuild().getCategoryById("662091757395247126").createTextChannel(e.getReaction().getReactionEmote().getEmoji()+"-" + String.valueOf(e.getMember().getIdLong())).queue(textChannel -> {
                    textChannel.sendMessage("Candidature pour : **Guide**").queue();
                    textChannel.sendMessage("**Faites votre candidature ici** " + e.getMember().getAsMention()).queue();
                    textChannel.createPermissionOverride(e.getMember()).setAllow(Permission.VIEW_CHANNEL).queue();

                });
                /*e.getGuild().createTextChannel(e.getReaction().getReactionEmote().getEmoji()+"-" + String.valueOf(e.getMember().getIdLong())).queue(textChannel -> {
                    textChannel.getManager().setParent(e.getGuild().getCategoryById("662088843951865887")).queue();
                    textChannel.sendMessage("Candidature pour : **Guide**").queue();
                    textChannel.sendMessage("**Faites votre candidature ici** " + e.getMember().getAsMention()).queue();
                });*/
            }
            if (e.getReaction().getReactionEmote().getEmoji().equals("\uD83D\uDCBE")) {
                e.getChannel().deleteMessageById(Main.userAndMessage.get(e.getMember().getIdLong())).queue();
                e.getGuild().getCategoryById("662091757395247126").createTextChannel(e.getReaction().getReactionEmote().getEmoji()+"-" + String.valueOf(e.getMember().getIdLong())).queue(textChannel -> {
                    textChannel.sendMessage("Candidature pour : **Game Designer**").queue();
                    textChannel.sendMessage("**Faites votre candidature ici** " + e.getMember().getAsMention()).queue();
                    textChannel.createPermissionOverride(e.getMember()).setAllow(Permission.VIEW_CHANNEL).queue();
                });
                /*e.getGuild().createTextChannel(e.getReaction().getReactionEmote().getEmoji()+"-" + String.valueOf(e.getMember().getIdLong())).queue(textChannel -> {
                    textChannel.getManager().setParent(e.getGuild().getCategoryById("662088843951865887")).queue();
                    textChannel.sendMessage("Candidature pour : **Guide**").queue();
                    textChannel.sendMessage("**Faites votre candidature ici** " + e.getMember().getAsMention()).queue();
                });*/
            }
            if (e.getReaction().getReactionEmote().getEmoji().equals("\uD83C\uDFB3")) {
                e.getChannel().deleteMessageById(Main.userAndMessage.get(e.getMember().getIdLong())).queue();
                e.getGuild().getCategoryById("662091757395247126").createTextChannel(e.getReaction().getReactionEmote().getEmoji()+"-" + String.valueOf(e.getMember().getIdLong())).queue(textChannel -> {
                    textChannel.sendMessage("Candidature pour : **Animateur**").queue();
                    textChannel.sendMessage("**Faites votre candidature ici** " + e.getMember().getAsMention()).queue();
                    textChannel.createPermissionOverride(e.getMember()).setAllow(Permission.VIEW_CHANNEL).queue();
                });
                /*e.getGuild().createTextChannel(e.getReaction().getReactionEmote().getEmoji()+"-" + String.valueOf(e.getMember().getIdLong())).queue(textChannel -> {
                    textChannel.getManager().setParent(e.getGuild().getCategoryById("662088843951865887")).queue();
                    textChannel.sendMessage("Candidature pour : **Guide**").queue();
                    textChannel.sendMessage("**Faites votre candidature ici** " + e.getMember().getAsMention()).queue();
                });*/
            }
            if (e.getReaction().getReactionEmote().getEmoji().equals("\uD83C\uDFA8")) {
                e.getChannel().deleteMessageById(Main.userAndMessage.get(e.getMember().getIdLong())).queue();
                e.getGuild().getCategoryById("662091757395247126").createTextChannel(e.getReaction().getReactionEmote().getEmoji()+"-" + String.valueOf(e.getMember().getIdLong())).queue(textChannel -> {
                    textChannel.sendMessage("Candidature pour : **Graphiste**").queue();
                    textChannel.sendMessage("**Faites votre candidature ici** " + e.getMember().getAsMention()).queue();
                    textChannel.createPermissionOverride(e.getMember()).setAllow(Permission.VIEW_CHANNEL).queue();
                });
                /*e.getGuild().createTextChannel(e.getReaction().getReactionEmote().getEmoji()+"-" + String.valueOf(e.getMember().getIdLong())).queue(textChannel -> {
                    textChannel.getManager().setParent(e.getGuild().getCategoryById("662088843951865887")).queue();
                    textChannel.sendMessage("Candidature pour : **Guide**").queue();
                    textChannel.sendMessage("**Faites votre candidature ici** " + e.getMember().getAsMention()).queue();
                });*/
            }
            if (e.getReaction().getReactionEmote().getEmoji().equals("\uD83C\uDFD7")) {
                e.getChannel().deleteMessageById(Main.userAndMessage.get(e.getMember().getIdLong())).queue();
                e.getGuild().getCategoryById("662091757395247126").createTextChannel(e.getReaction().getReactionEmote().getEmoji()+"-" + String.valueOf(e.getMember().getIdLong())).queue(textChannel -> {
                    textChannel.sendMessage("Candidature pour : **Constructeur**").queue();
                    textChannel.sendMessage("**Faites votre candidature ici** " + e.getMember().getAsMention()).queue();
                    textChannel.createPermissionOverride(e.getMember()).setAllow(Permission.VIEW_CHANNEL).queue();                });
                /*e.getGuild().createTextChannel(e.getReaction().getReactionEmote().getEmoji()+"-" + String.valueOf(e.getMember().getIdLong())).queue(textChannel -> {
                    textChannel.getManager().setParent(e.getGuild().getCategoryById("662088843951865887")).queue();
                    textChannel.sendMessage("Candidature pour : **Guide**").queue();
                    textChannel.sendMessage("**Faites votre candidature ici** " + e.getMember().getAsMention()).queue();
                });*/
            }
        } else {return;}
    }

    private void onM(MessageReceivedEvent e){
        if(e.getAuthor().equals(e.getJDA().getSelfUser())) return;
        if(e.getChannel().getId().equals("701754368499646565") || e.getChannel().getId().equals("701079779692118116")) {
            String[] args = e.getMessage().getContentRaw().split(" ");
            if (args[0].equalsIgnoreCase("!link")) {
                if (args[1] == null || args[2] == null) {
                    e.getChannel().sendMessage(e.getAuthor().getAsTag() + " Usage : !link <Pseudo> <Token>\n-> Pour connaître votre token, éxecutez la commande **/link** en jeu.").queue();
                }
                final String playerName = args[1];
                final String token = args[2];
                final DbConnection userConnection = Main.getDbManager().getUserConnection();
                try {
                    Connection connection = userConnection.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM fastfinder WHERE username = ?");
                    preparedStatement.setString(1, playerName);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        final String targetUUID = resultSet.getString("uuid");
                        preparedStatement = connection.prepareStatement("SELECT * FROM discord_linker WHERE uuid = ?");
                        preparedStatement.setString(1,targetUUID);
                        resultSet = preparedStatement.executeQuery();
                        //Resultset
                        if(resultSet.next()) {
                            if (resultSet.getInt("state") == 1) {
                                e.getChannel().sendMessage(e.getAuthor().getAsTag() + " Ce compte est déjà lié.").queue();
                                return;
                            } else {
                                if(token.equals(resultSet.getString("token"))){
                                    e.getChannel().sendMessage(e.getAuthor().getAsTag() + " **Compte lié** !");
                                    preparedStatement = connection.prepareStatement("UPDATE discord_linker SET state = ? WHERE uuid = ?");
                                    preparedStatement.setInt(1, 1);
                                    preparedStatement.setString(2, targetUUID);
                                    preparedStatement.executeUpdate();

                                    PreparedStatement preparedStatement3 = connection.prepareStatement("INSERT INTO discord_accounts VALUES (?,?)");
                                    preparedStatement3.setLong(1, e.getAuthor().getIdLong());
                                    preparedStatement3.setString(2, targetUUID);
                                    preparedStatement3.executeUpdate();
                                } else {
                                    e.getChannel().sendMessage(e.getAuthor().getAsTag() + " Token incorrect.").queue();
                                    return;
                                }
                            }
                        }
                    } else {
                        e.getChannel().sendMessage(e.getAuthor().getAsTag() + " Ce compte ne s'est jamais connecté sur le serveur.").queue();
                        return;
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}

package fr.pablo.bot.data.cache;

import fr.pablo.bot.Main;
import fr.pablo.bot.data.cache.cache_objects.IDLearner;
import fr.pablo.bot.data.cache.cache_objects.Team;
import fr.pablo.bot.data.database.DbConnection;
import net.dv8tion.jda.api.entities.Guild;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CacheSystem {

    public static Map<String, IDLearner> usersCache = new HashMap<>();
    public static Map<Integer, Team> teamCache = new HashMap<>();
    public static Map<Integer, fr.pablo.bot.teams.Team> teamDataCache = new HashMap<>();


    public static void initialize() throws SQLException, ClassNotFoundException {
        Guild guild = Main.jda.getGuildById(741640428335661071L);

        Main.jda.getTextChannelById(742434704199909527L).sendMessage("\uD83D\uDFE7 • Mise en cache de la base de donnée..").queue();
        DbConnection userConnection = Main.getDbManager().getUserConnection();
        Connection connection = userConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            IDLearner idLearner = new IDLearner(rs.getString("id"),rs.getString("name"), rs.getInt("level"), rs.getInt("EXP"), rs.getInt("maxEXP"), rs.getInt("notions"), rs.getInt("actualNotion"), rs.getInt("terminatedTutorials"), rs.getInt("actualTutorial"), rs.getInt("team"));
            usersCache.put(idLearner.getId(), idLearner);
        }

        Main.jda.getTextChannelById(742434704199909527L).sendMessage("\uD83D\uDFE8 • Mise en cache de "+usersCache.size()+" utilisateurs ["+usersCache.size()+"/"+guild.getMembers().size()+"]").queue();

        preparedStatement = connection.prepareStatement("SELECT * FROM teams");
        rs = preparedStatement.executeQuery();
        while (rs.next()){
            teamCache.put(rs.getInt("id"), new Team(rs.getInt("id"),rs.getInt("pts")));
            teamDataCache.put(rs.getInt("id"), fr.pablo.bot.teams.Team.values()[rs.getInt("id")]);
        }


        Main.jda.getTextChannelById(742434704199909527L).sendMessage("\uD83D\uDFE8 • Mise en cache de "+teamCache.size()+" équipes").queue();
        Main.jda.getTextChannelById(742434704199909527L).sendMessage("\uD83D\uDFE9 • Mise en cache terminée").queue();
    }
}

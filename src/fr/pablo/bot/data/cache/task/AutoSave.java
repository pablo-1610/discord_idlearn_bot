package fr.pablo.bot.data.cache.task;

import fr.pablo.bot.Main;
import fr.pablo.bot.data.cache.CacheSystem;
import fr.pablo.bot.data.cache.cache_objects.IDLearner;
import fr.pablo.bot.data.cache.cache_objects.Team;
import fr.pablo.bot.data.database.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TimerTask;

public class AutoSave extends TimerTask {
    @Override
    public void run() {
        try {
            DbConnection userConnection = Main.getDbManager().getUserConnection();
            Connection connection = userConnection.getConnection();

            for (Map.Entry<String, IDLearner> entry : CacheSystem.usersCache.entrySet()) {
                IDLearner a = entry.getValue();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET EXP = ?, maxEXP = ?, notions = ?, actualNotion = ?, terminatedTutorials = ?, actualTutorial = ? WHERE id = ?");
                preparedStatement.setInt(1,a.getEXP());
                preparedStatement.setInt(2,a.getMaxEXP());
                preparedStatement.setInt(3,a.getNotions());
                preparedStatement.setInt(4,a.getActualNotion());
                preparedStatement.setInt(5,a.getTerminatedTutorials());
                preparedStatement.setInt(6,a.getActualTutorial());
                preparedStatement.setString(7,a.getId());
                preparedStatement.executeUpdate();
            }

            for (Map.Entry<Integer, Team> entry : CacheSystem.teamCache.entrySet()) {
                Team a = entry.getValue();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE teams SET pts = ? WHERE id = ?");
                preparedStatement.setInt(1,a.getPts());
                preparedStatement.setInt(2,a.getId());
                preparedStatement.executeUpdate();
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        Main.jda.getTextChannelById(742434704199909527L).sendMessage("\uD83D\uDFEA • Caches envoyés aux bases de données.").queue();
    }
}

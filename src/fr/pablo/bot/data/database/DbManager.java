package fr.pablo.bot.data.database;

import java.sql.SQLException;

public class DbManager {
    private DbConnection userConnection;
    public DbManager(){
        this.userConnection = new DbConnection(new DbCredentials("localhost","admin","80Ivg5Swn3","idlearn",3306));
    }

    public DbConnection getUserConnection() {
        return userConnection;
    }

    public void close() {
        try {
            this.userConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

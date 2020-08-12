package fr.pablo.bot.data.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DbConnection {
    private DbCredentials dbCredentials;
    private Connection connection;

    public DbConnection(DbCredentials dbCredentials){
        this.dbCredentials = dbCredentials;
        try {
            this.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void connect() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        this.connection = DriverManager.getConnection(this.dbCredentials.toURI(), this.dbCredentials.getUser(), this.dbCredentials.getPass());
        System.out.println("Connecte a la DB");
    }

    public void close() throws SQLException {
        if(this.connection != null){
            if(!this.connection.isClosed()){
                this.connection.close();
            }
        }
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        if(this.connection != null){
            if(!this.connection.isClosed()){
                return this.connection;
            }
        }
        connect();
        return this.connection;
    }
}

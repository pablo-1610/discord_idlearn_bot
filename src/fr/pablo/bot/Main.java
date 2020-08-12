package fr.pablo.bot;

import fr.pablo.bot.data.database.DbManager;
import fr.pablo.bot.trigger.handler.EventsHandler;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import okhttp3.OkHttpClient;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Main {
    private static DbManager dbManager;
    public static JDA jda;
    public static Map<Long, Long> userAndMessage = new HashMap<>();
    public static void main(String[] args) {
        try{
            OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS);
            jda = new JDABuilder(AccountType.BOT).setToken("NzQyMzEyMjkwNTQzNzMwNjkw.XzESMQ.SvUaOx5YCTSUJA_B56gpy7M-SAE").setActivity(Activity.watching("votre progression")).setHttpClientBuilder(httpBuilder).build();
            jda.setAutoReconnect(true);

            jda.addEventListener(new EventsHandler());
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            dbManager = new DbManager();

            System.out.println(jda.getGuilds().size());
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static DbManager getDbManager() {
        return dbManager;
    }

    public static void setDbManager(DbManager dbManager) {
        Main.dbManager = dbManager;
    }
}

package fr.pablo.bot.teams;

import fr.pablo.bot.Main;
import fr.pablo.bot.data.cache.CacheSystem;
import fr.pablo.bot.data.cache.cache_objects.Team;
import fr.pablo.bot.task.SeasonTaskA;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TimerTask;

public class PointsCounter {
    public static long messageID;
    public static void initialize(){

        Main.jda.getTextChannelById(742422811284340766L).deleteMessageById(Main.jda.getTextChannelById(742422811284340766L).getLatestMessageId()).queue();

        Main.jda.getTextChannelById(742422811284340766L).sendMessage("Chargement des données d'équipe...").queue(message -> {
            messageID = message.getIdLong();
        });
    }

    public static class Counter extends TimerTask {

        @Override
        public void run() {
            DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR_OF_DAY, 2);
            date = calendar.getTime();

            System.out.println(format.format(date));

            String a = "\\❤️ • Équipe Rouge: **"+CacheSystem.teamCache.get(0).getPts()+" pts**\n";
            String b = "\\\uD83D\uDC99 • Équipe Bleue: **"+CacheSystem.teamCache.get(1).getPts()+" pts**\n";
            String c = "\uD83D\uDC9B️ • Équipe Jaune: **"+CacheSystem.teamCache.get(2).getPts()+" pts**\n";
            String d = "\uD83D\uDC9A️ • Équipe Verte: **"+CacheSystem.teamCache.get(3).getPts()+" pts**\n";

            Main.jda.getTextChannelById(742422811284340766L).editMessageById(messageID,"**Classement des équipes**:\n\n"+a+b+c+d+"\nDernière mise à jour: __" + format.format(date) +"__").queue();
        }
    }
}

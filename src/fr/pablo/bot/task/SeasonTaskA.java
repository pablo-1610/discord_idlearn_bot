package fr.pablo.bot.task;

import fr.pablo.bot.Main;
import fr.pablo.bot.config.Season;
import net.dv8tion.jda.api.entities.Activity;

import java.util.TimerTask;

public class SeasonTaskA extends TimerTask {
    private int a = 1;
    @Override
    public void run() {
        if (a == 0) {
            Main.jda.getPresence().setActivity(Activity.watching("la {Saison " + Season.s + "}"));
            a = 1;
        } else {
            Main.jda.getPresence().setActivity(Activity.watching(Season.sT));
            a = 0;
        }



    }
}

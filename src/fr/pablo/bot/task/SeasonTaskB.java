package fr.pablo.bot.task;

import fr.pablo.bot.Main;
import fr.pablo.bot.config.Season;

import java.util.TimerTask;

public class SeasonTaskB extends TimerTask {
    private int b = 0;
    @Override
    public void run() {
        if (b == 0) {
            Main.jda.getVoiceChannelById(742345946201063547L).getManager().setName("Apprenez !").queue();
            b = 1;
            System.out.println("[DEBUG] Voice channel to -> Apprenez !");
        } else if (b == 1){
            Main.jda.getVoiceChannelById(742345946201063547L).getManager().setName("Codez !").queue();
            b = 2;
            System.out.println("[DEBUG] Voice channel to -> Codez !");
        } else {
            Main.jda.getVoiceChannelById(742345946201063547L).getManager().setName("Créez !").queue();
            b = 0;
            System.out.println("[DEBUG] Voice channel to -> Créez !");
        }
    }
}

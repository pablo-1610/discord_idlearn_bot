package fr.pablo.bot.trigger.list;

import fr.pablo.bot.config.Season;
import fr.pablo.bot.data.cache.CacheSystem;
import fr.pablo.bot.data.cache.task.AutoSave;
import fr.pablo.bot.task.SeasonTaskA;
import fr.pablo.bot.teams.PointsCounter;
import net.dv8tion.jda.api.events.GenericEvent;

import java.sql.SQLException;
import java.util.Timer;

public class Ready {
    public Ready(GenericEvent e) {
        System.out.println("[---------------------------------------]\n\n\nBot Ready\n\n\n[---------------------------------------]");
        e.getJDA().getTextChannelById(742329577258483732L).addReactionById(e.getJDA().getTextChannelById(742329577258483732L).getLatestMessageId(),"✅").queue();
        e.getJDA().getVoiceChannelById(742341538042085457L).getManager().setName("Saison "+ Season.s).queue();
        e.getJDA().getVoiceChannelById(742341868150456391L).getManager().setName(Season.sT).queue();
        PointsCounter.initialize();
        Timer timer;
        timer = new Timer();
        timer.schedule(new SeasonTaskA(), 0, 5000);
        timer.schedule(new AutoSave(), 6000,60000*3);
        timer.schedule(new PointsCounter.Counter(), 6000,60000);
        try {
            CacheSystem.initialize();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}

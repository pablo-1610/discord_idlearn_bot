package fr.pablo.bot.trigger.list;

import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;

public class Quit {
    public Quit(GuildMemberLeaveEvent e) {
        e.getGuild().getTextChannelById(742370127907127407L).sendMessage("\uD83D\uDD34 • " + e.getMember().getAsMention()+" ["+e.getMember().getUser().getAsTag()+"] à **quitté** le Discord").queue();
    }
}

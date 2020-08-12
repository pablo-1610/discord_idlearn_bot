package fr.pablo.bot.trigger.list;

import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;

public class Join {
    public Join(GuildMemberJoinEvent e) {
        e.getGuild().getTextChannelById(742370127907127407L).sendMessage("\uD83D\uDFE2 • " + e.getMember().getAsMention()+" ["+e.getMember().getUser().getAsTag()+"] à **rejoint** le Discord").queue();
    }
}

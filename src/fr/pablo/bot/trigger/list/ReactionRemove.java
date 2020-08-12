package fr.pablo.bot.trigger.list;

import fr.pablo.bot.modules.autoevents.EventSelector;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;

public class ReactionRemove {
    public ReactionRemove(MessageReactionRemoveEvent e) {
        if (e.getMember().getUser().equals(e.getJDA().getSelfUser())) return;
        if(e.getChannel().getId().equals(String.valueOf(743127101359521802L))){
            EventSelector.reactRmv(e);
            return;
        }
    }

}

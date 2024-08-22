package flandre.scarlet.thevoidkingdom.functions.bot;

import com.google.common.eventbus.Subscribe;
import com.molean.isletopia.framework.annotations.Bean;
import com.molean.tencent.channelbot.event.EventIdEvent;
import com.molean.tencent.channelbot.event.GuildMemberUpdateEvent;
import flandre.scarlet.thevoidkingdom.utils.framework.BotListener;

@Bean
public class EventIdListener implements BotListener {
    public static String EVENT_ID;
    public static Long LAST_UPDATE = 0L;


    @Subscribe
    public void on(EventIdEvent event) {
        if (event.getEvent() instanceof GuildMemberUpdateEvent) {
            if (System.currentTimeMillis() - LAST_UPDATE > 3 * 60 * 1000L) {
                EVENT_ID = event.getId();
                LAST_UPDATE = System.currentTimeMillis();
            }
        }
    }
}

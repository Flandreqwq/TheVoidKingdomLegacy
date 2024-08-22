package flandre.scarlet.thevoidkingdom.functions.bot;

import com.molean.isletopia.framework.annotations.AutoInject;
import com.molean.isletopia.framework.annotations.Bean;
import com.molean.tencent.channelbot.Bot;
import flandre.scarlet.thevoidkingdom.utils.framework.IntervalTask;

@Bean
public class EventIdTask {
    private static final String guildId = "1653041474839589203";
    private static final String testUserId = "7606083525457298388";
    private static final String testRoleId = "15020893";
    @AutoInject
    private Bot bot;

    @IntervalTask(value = 20 * 60 * 3, delay = 1, isAsynchronously = true)
    public void task() {
        bot.getRoleService().addMember(guildId, testUserId, testRoleId, null);
    }
}

package flandre.scarlet.thevoidkingdom.utils.framework;

import com.molean.isletopia.framework.annotations.AutoInject;
import com.molean.isletopia.framework.annotations.BeanHandler;
import com.molean.isletopia.framework.annotations.IBeanHandler;
import com.molean.tencent.channelbot.Bot;

@BeanHandler
public class BotListenerHandler implements IBeanHandler {
    @AutoInject
    private Bot bot;

    @Override
    public void handle(Object o) {
        if (o instanceof BotListener botListener) {
            bot.getEventBus().register(botListener);
        }
    }
}
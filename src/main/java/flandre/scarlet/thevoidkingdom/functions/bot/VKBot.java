package flandre.scarlet.thevoidkingdom.functions.bot;

import co.aikar.commands.QQProCommandManager;
import com.molean.isletopia.framework.ClassResolver;
import com.molean.isletopia.framework.annotations.Bean;
import com.molean.tencent.channelbot.Bot;
import com.molean.tencent.channelbot.BotAccess;
import com.molean.tencent.channelbot.BotFactory;
import flandre.scarlet.thevoidkingdom.utils.framework.BukkitClassScanner;

import java.util.Locale;

@Bean
public class VKBot {
    private static final String botId = "102009615";
    private static final String token = "ipm1GwRZQeHvYuwi6qOQ7AcV4jSlse8x";

    @Bean
    public Bot bot(BukkitClassScanner bukkitClassScanner) {
        BotAccess botAccess = new BotAccess(botId, token, null, false);
        Bot bot = BotFactory.createBot(botAccess, null, ClassResolver.INSTANCE, bukkitClassScanner);
        bot.getEventBus().register(bot.getBotCommandMap());
        return bot;
    }

    @Bean
    public QQProCommandManager botCommandManager(com.molean.tencent.channelbot.Bot bot) {
        QQProCommandManager qqProCommandManager = new QQProCommandManager(bot);
        qqProCommandManager.enableUnstableAPI("help");
        qqProCommandManager.getLocales().setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return qqProCommandManager;
    }
}
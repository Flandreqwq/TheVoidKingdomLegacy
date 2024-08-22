package flandre.scarlet.thevoidkingdom.functions.bot;

import com.google.common.eventbus.Subscribe;
import com.molean.isletopia.framework.annotations.AutoInject;
import com.molean.isletopia.framework.annotations.Bean;
import com.molean.tencent.channelbot.Bot;
import com.molean.tencent.channelbot.event.MessageCreateEvent;
import flandre.scarlet.thevoidkingdom.utils.framework.BotListener;
import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@Bean
public class ChannelChatListener implements BotListener {
    @AutoInject
    private Bot bot;

    public String checkEmoji(String content, Player player, String emojiName) {
        if (!player.hasPermission("ia.user.image.use." + emojiName)) {
            return content.replace(":" + emojiName + ":", emojiName);
        }
        return content;
    }

    @Subscribe
    public void on(MessageCreateEvent event) {
        String content = event.getContent();
        if (!event.getChannelId().equals("7401459")) {
            return;
        }
        if (content != null) {
//            char[] chars = content.toCharArray();
//            int first = 0;
//            int second;
//            boolean isFirst = true;
//            for (int i = 0; i < chars.length; i++) {
//                if (chars[i] == ':') {
//                    if (isFirst) {
//                        first = i;
//                    } else {
//                        second = i;
//                        String emojiName = content.substring(first + 1, second);
//                    }
//                    isFirst = !isFirst;
//                }
//            }
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                onlinePlayer.sendMessage(TheVoidKingdomUtils.deserializeMiniMessage(
                        "<gray><italic>" + event.getMember().getNick() + "</italic><dark_gray> >> " + "<white>" + content, false)
                );
            }
        }
    }
}

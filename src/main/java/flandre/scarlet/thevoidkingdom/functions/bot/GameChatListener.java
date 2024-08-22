package flandre.scarlet.thevoidkingdom.functions.bot;

import com.molean.isletopia.framework.annotations.AutoInject;
import com.molean.isletopia.framework.annotations.Bean;
import com.molean.tencent.channelbot.Bot;
import com.molean.tencent.channelbot.post.CreateMessage;
import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;


@Bean
public class GameChatListener implements Listener {
    @AutoInject
    private Bot bot;

    @EventHandler(priority = EventPriority.HIGH)
    public void on(AsyncChatEvent event) {
        if (event.isCancelled()) {
            return;
        }
        Player player = event.getPlayer();
        event.setCancelled(true);
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.sendMessage(TheVoidKingdomUtils.deserializeMiniMessage(
                    "<gray>" + player.getName() + "<dark_gray> >> ", false).append(event.message().color(NamedTextColor.WHITE))
            );
        }
        TextComponent textComponent = (TextComponent) event.message();
        CreateMessage createMessage = new CreateMessage();
        createMessage.setContent(player.getName() + " >> " + textComponent.content());
        createMessage.setMsgId(EventIdListener.EVENT_ID);
        try {
            bot.getMessageService().createMessage("7401459", createMessage);
        } catch (RuntimeException ignored) {
        }
    }
}

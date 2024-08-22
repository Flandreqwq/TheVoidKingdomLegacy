package flandre.scarlet.thevoidkingdom.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.utils.framework.PaperCommand;
import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;

@Bean
@CommandAlias("vk|flandre")
@Subcommand("rgbmessage")
@CommandPermission("vk.admin.rgbmessage")
@Description("向自己聊天栏发送RGB颜色消息")
public class RGBMessageCommand extends BaseCommand implements PaperCommand {
    @Default
    public void onDefault(Player player, String message, int r, int g, int b) {
        player.sendMessage(Component.text(message).color(TextColor.color(r, g, b)));
        TheVoidKingdomUtils.sendMessageToPlayer(player, "<yellow>已发送", true);
    }
}

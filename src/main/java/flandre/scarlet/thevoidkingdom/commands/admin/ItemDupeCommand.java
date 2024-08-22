package flandre.scarlet.thevoidkingdom.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.utils.framework.PaperCommand;
import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Bean
@CommandAlias("vk|flandre")
@Subcommand("item dupe")
@CommandPermission("vk.admin.item.dupe")
@Description("复制手中物品")
public class ItemDupeCommand extends BaseCommand implements PaperCommand {
    @Default
    public void onDefault(Player player, int amount) {
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if (itemStack.getType().isAir()) {
            TheVoidKingdomUtils.sendMessageToPlayer(player, "<red>你手中空无一物", true);
            return;
        }
        if (amount > 64 || amount < 1) {
            TheVoidKingdomUtils.sendMessageToPlayer(player, "<red>复制数量不合法", true);
            return;
        }
        ItemStack dupeItemStack = itemStack.clone();
        dupeItemStack.setAmount(amount);
        Location location = player.getLocation();
        player.getWorld().dropItem(location, dupeItemStack);
        TheVoidKingdomUtils.sendMessageToPlayer(player, "<yellow>物品复制成功", true);
    }
}

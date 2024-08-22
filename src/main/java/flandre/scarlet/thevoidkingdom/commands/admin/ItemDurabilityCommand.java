package flandre.scarlet.thevoidkingdom.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.utils.game.MMOItemsUtils;
import flandre.scarlet.thevoidkingdom.utils.framework.PaperCommand;
import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

@Bean
@CommandAlias("vk|flandre")
@Subcommand("item durability")
@CommandPermission("vk.admin.item.durability")
@Description("修改手中物品耐久")
public class ItemDurabilityCommand extends BaseCommand implements PaperCommand {
    @Default
    public void onDefault(Player player, int durability, int maxDurability) {
        PlayerInventory inventory = player.getInventory();
        ItemStack itemStack = inventory.getItemInMainHand();
        int slot = inventory.getHeldItemSlot();
        if (itemStack.getType().isAir()) {
            TheVoidKingdomUtils.sendMessageToPlayer(player, "<red>你手中空无一物", true);
            return;
        }
        if (MMOItemsUtils.getMaxDurability(itemStack) == -1) {
            TheVoidKingdomUtils.sendMessageToPlayer(player, "<red>该物品无耐久值", true);
            return;
        }
        inventory.setItem(slot, MMOItemsUtils.changeDurability(itemStack, durability, maxDurability));
        TheVoidKingdomUtils.sendMessageToPlayer(player, "<yellow>修改成功", true);
        TheVoidKingdomUtils.sendMessageToPlayer(player, "<yellow>修改后的耐久值为:" + durability, true);
        TheVoidKingdomUtils.sendMessageToPlayer(player, "<yellow>修改后的最大耐久为:" + maxDurability, true);
    }
}

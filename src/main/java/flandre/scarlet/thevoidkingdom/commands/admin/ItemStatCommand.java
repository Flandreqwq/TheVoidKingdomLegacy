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
@Subcommand("item stat")
@CommandPermission("vk.admin.item.stat")
@Description("修改手中物品stat数值")
public class ItemStatCommand extends BaseCommand implements PaperCommand {

    @Default
    public void onDefault(Player player, String stat, double value) {
        PlayerInventory inventory = player.getInventory();
        ItemStack itemStack = inventory.getItemInMainHand();
        if (itemStack.getType().isAir()) {
            TheVoidKingdomUtils.sendMessageToPlayer(player, "<red>你手中空无一物", true);
            return;
        }
        if (!MMOItemsUtils.CHANGEABLE_STAT_TRANSLATE_MAP.containsValue(stat)
                && !MMOItemsUtils.CHANGEABLE_STAT_TRANSLATE_MAP.containsKey(stat)) {
            TheVoidKingdomUtils.sendMessageToPlayer(player, "<red>不存在的stat", true);
            TheVoidKingdomUtils.sendMessageToPlayer(player, "<yellow>可用的stat列表:", true);
            MMOItemsUtils.CHANGEABLE_STAT_TRANSLATE_MAP.forEach((statChineseName, statName) -> {
                TheVoidKingdomUtils.sendMessageToPlayer(player, "<aqua>" + statName + " <yellow>|<aqua> " + statChineseName, true);
            });
            return;
        }
        int slot = inventory.getHeldItemSlot();
        ItemStack newItem = MMOItemsUtils.changeStat(itemStack, stat, value);
        inventory.setItem(slot, newItem);
        TheVoidKingdomUtils.sendMessageToPlayer(player, "<yellow>修改成功", true);
        TheVoidKingdomUtils.sendMessageToPlayer(player, "<yellow>修改后的 <aqua>" + stat + " <yellow>值为:" + value, true);
    }
}

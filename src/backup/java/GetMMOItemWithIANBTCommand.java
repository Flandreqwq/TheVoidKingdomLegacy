package flandre.scarlet.thevoidkingdom.commands.player;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.molean.isletopia.framework.annotations.Bean;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.utils.framework.PaperCommand;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@Bean
@CommandAlias("vk|flandre")
@Subcommand("player getMMOItemWithIANBT")
@CommandPermission("thevoidkingdom.command.player.getiaitem")
@Description("获取ia物品")
public class GetMMOItemWithIANBTCommand extends BaseCommand implements PaperCommand {
    @Default
    public void onDefault(Player player, String miType, String miName, String namespace, String id) {
        MMOItem mmoItem = MMOItems.plugin.getMMOItem(MMOItems.plugin.getTypes().get(miType), miName);
        if (mmoItem == null) {
            TheVoidKingdom.LOGGER.warning("没有找到Type为 " + miType + " Name为 " + miName + " 的mi物品");
            return;
        }
        ItemStack itemStack = mmoItem.newBuilder().build();
        assert itemStack != null;
        NBTItem nbtItem = new NBTItem(itemStack);
        NBTCompound nbtCompound = nbtItem.addCompound("itemsadder");
        nbtCompound.setString("namespace", namespace);
        nbtCompound.setString("id", id);
        ItemStack newItem = nbtItem.getItem();
        Inventory inventory = player.getInventory();
        int slot = inventory.firstEmpty();
        if (slot == -1) {
            player.getWorld().dropItem(player.getLocation(), newItem);
        } else {
            inventory.setItem(slot, newItem);
        }
    }
}

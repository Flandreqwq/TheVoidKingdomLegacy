package flandre.scarlet.thevoidkingdom.functions.item.vanilla;

import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;


@Bean
public class VanillaStonePickaxeListener implements Listener {
    @EventHandler(priority = EventPriority.LOW)
    public void on(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        Material type = itemStack.getType();
        if (!type.equals(Material.STONE_PICKAXE)) {
            return;
        }
        if (ItemsAdderUtils.isCustomItemStack(itemStack)) {
            return;
        }
        Block block = event.getBlock();
        String blockType = block.getType().toString();
        if (blockType.contains("IRON")) {
            event.setDropItems(false);
        }
    }
}

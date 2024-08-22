package flandre.scarlet.thevoidkingdom.functions.item.vanilla;

import com.molean.isletopia.framework.annotations.Bean;
import io.lumine.mythic.lib.api.item.NBTItem;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

@Bean
public class VanillaArmorWashListener implements Listener {
    @EventHandler(priority = EventPriority.LOW)
    public void on(PlayerInteractEvent event) {
        if (!event.getAction().isRightClick()) {
            return;
        }
        Block block = event.getClickedBlock();
        if (block == null) {
            return;
        }
        if (!block.getType().equals(Material.WATER_CAULDRON)) {
            return;
        }
        ItemStack itemStack = event.getItem();
        if (itemStack == null) {
            return;
        }
        NBTItem nbtItem = NBTItem.get(itemStack);
        if (!nbtItem.hasType()) {
            return;
        }
        switch (nbtItem.getType()) {
            case "ARMOR", "ARMOR_WARRIOR", "ARMOR_ARCHER", "ARMOR_MAGE" -> event.setCancelled(true);
        }
    }
}

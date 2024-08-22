package flandre.scarlet.thevoidkingdom.functions.item.custom;

import com.molean.isletopia.framework.annotations.Bean;
import io.lumine.mythic.lib.api.item.NBTItem;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Bean
public class DisableMaterialAxeStripListener implements Listener {
    public static final List<String> list = List.of("_LOG", "_WOOD", "_STEM", "_HYPHAE");

    @EventHandler(priority = EventPriority.LOW)
    public void on(PlayerInteractEvent event) {
        if (!event.getAction().isRightClick()) {
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
        String miType = nbtItem.getType();
        if (!"HAMMER".equals(miType) && !"SWORD".equals(miType)) {
            return;
        }
        Block block = event.getClickedBlock();
        if (block == null) {
            return;
        }
        String materialStr = block.getType().toString();
        if (materialStr.contains("STRIPPED_")) {
            return;
        }
        for (String str : list) {
            if (materialStr.contains(str)) {
                event.setCancelled(true);
                return;
            }
        }
    }
}

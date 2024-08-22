package flandre.scarlet.thevoidkingdom.functions.block.vanilla;

import com.molean.isletopia.framework.annotations.Bean;
import dev.lone.itemsadder.api.CustomStack;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

import java.util.List;
import java.util.Objects;

@Bean
public class LogStripListener implements Listener {
    private static final List<Material> logs = List.of(
            Material.OAK_LOG,
            Material.SPRUCE_LOG,
            Material.BIRCH_LOG,
            Material.JUNGLE_LOG,
            Material.ACACIA_LOG,
            Material.DARK_OAK_LOG,
            Material.MANGROVE_LOG,
            Material.CRIMSON_STEM,
            Material.WARPED_STEM
    );
    private static final List<Material> strippedLogs = List.of(
            Material.STRIPPED_OAK_LOG,
            Material.STRIPPED_SPRUCE_LOG,
            Material.STRIPPED_BIRCH_LOG,
            Material.STRIPPED_JUNGLE_LOG,
            Material.STRIPPED_ACACIA_LOG,
            Material.STRIPPED_DARK_OAK_LOG,
            Material.STRIPPED_MANGROVE_LOG,
            Material.STRIPPED_CRIMSON_STEM,
            Material.STRIPPED_WARPED_STEM
    );

    private static final List<String> barks = List.of(
            "vkmaterials:xiangmushupi",
            "vkmaterials:yunshanshupi",
            "vkmaterials:baihuashupi",
            "vkmaterials:conglinshupi",
            "vkmaterials:jinhehuanshupi",
            "vkmaterials:shensexiangmushupi",
            "vkmaterials:hongshushupi",
            "vkmaterials:feihongjunpi",
            "vkmaterials:guiyijunpi"
    );

    @EventHandler(priority = EventPriority.HIGH)
    public void on(EntityChangeBlockEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (!event.getEntityType().equals(EntityType.PLAYER)) {
            return;
        }
        Block block = event.getBlock();
        Material material = block.getType();
        int index;
        if (logs.contains(material)) {
            index = logs.indexOf(material);
        } else {
            return;
        }
        if (!Objects.equals(event.getTo(), strippedLogs.get(index))) {
            return;
        }
        CustomStack customStack = CustomStack.getInstance(barks.get(index));
        if (customStack == null) {
            TheVoidKingdom.LOGGER.warning("不存在的ia物品: " + barks.get(index));
        } else {
            block.getWorld().dropItem(block.getLocation().add(0.5, 0.5, 0.5), customStack.getItemStack());
        }
    }
}

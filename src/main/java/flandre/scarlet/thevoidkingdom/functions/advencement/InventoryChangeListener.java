package flandre.scarlet.thevoidkingdom.functions.advencement;

import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

@Bean
public class InventoryChangeListener implements Listener {
    private static final Map<Material, AdvancementCriteria> map = new HashMap<>();

    static {
        map.put(Material.STONE_PICKAXE, new AdvancementCriteria(
                new NamespacedKey("vkadvancements", "universal/upgrade_tools"), "石镐")
        );
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void on(PlayerInventorySlotChangeEvent event) {
        ItemStack itemStack = event.getNewItemStack();
        AdvancementCriteria advancementCriteria = map.get(itemStack.getType());
        if (advancementCriteria == null) {
            return;
        }
        Player player = event.getPlayer();
        if (advancementCriteria.isIgnoredPlayer(player)) {
            return;
        }
        if (ItemsAdderUtils.getItemNamespaceId(itemStack).contains(":")) {
            return;
        }
        Advancement advancement = Bukkit.getAdvancement(advancementCriteria.getAdvancementKey());
        if (advancement == null) {
            TheVoidKingdom.LOGGER.warning("不存在的进度: " + advancementCriteria.getAdvancementKey().toString());
            return;
        }
        AdvancementProgress advancementProgress = player.getAdvancementProgress(advancement);
        String criteria = advancementCriteria.getCriteria();
        if (!advancementProgress.getRemainingCriteria().contains(criteria)) {
            advancementCriteria.addIgnoredPlayer(player);
            return;
        }
        advancementProgress.awardCriteria(criteria);
    }
}

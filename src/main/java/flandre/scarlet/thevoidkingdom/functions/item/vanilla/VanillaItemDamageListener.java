package flandre.scarlet.thevoidkingdom.functions.item.vanilla;

import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Bean
public class VanillaItemDamageListener implements Listener {
    private static final List<Material> list = List.of(
            Material.IRON_AXE,
            Material.IRON_PICKAXE,
            Material.IRON_HOE,
            Material.IRON_SHOVEL,
            Material.IRON_SWORD,
            Material.GOLDEN_AXE,
            Material.GOLDEN_PICKAXE,
            Material.GOLDEN_HOE,
            Material.GOLDEN_SHOVEL,
            Material.GOLDEN_SWORD,
            Material.DIAMOND_AXE,
            Material.DIAMOND_PICKAXE,
            Material.DIAMOND_HOE,
            Material.DIAMOND_SHOVEL,
            Material.DIAMOND_SWORD,
            Material.NETHERITE_AXE,
            Material.NETHERITE_PICKAXE,
            Material.NETHERITE_HOE,
            Material.NETHERITE_SHOVEL,
            Material.NETHERITE_SWORD,
            Material.BOW,
            Material.CROSSBOW,
            Material.SHIELD
    );

    @EventHandler(priority = EventPriority.LOWEST)
    public void on(PlayerItemDamageEvent event) {
        if (event.isCancelled()) {
            return;
        }
        ItemStack itemStack = event.getItem();
        if (ItemsAdderUtils.isCustomItemStack(itemStack)) {
            return;
        }
        Material material = itemStack.getType();
        if (list.contains(material)) {
            event.setDamage(material.getMaxDurability());
            TheVoidKingdomUtils.sendMessageToPlayer(event.getPlayer(), "<red>一股神秘的力量导致这个物品异常脆弱……", false);
        }
    }
}

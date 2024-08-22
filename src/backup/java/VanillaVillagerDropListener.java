package flandre.scarlet.thevoidkingdom.functions.item.vanilla;

import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.utils.TheVoidKingdomUtils;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.inventory.ItemStack;

@Bean
public class VanillaVillagerDropListener implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void on(EntityDropItemEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (!event.getEntityType().equals(EntityType.VILLAGER)) {
            return;
        }
        Item item = event.getItemDrop();
        ItemStack itemStack = item.getItemStack();
        Material material = itemStack.getType();
        if (TheVoidKingdomUtils.VANILLA_ARMORS.contains(material) ||
                TheVoidKingdomUtils.VANILLA_TOOL.contains(material) ||
                TheVoidKingdomUtils.VANILLA_WEAPON.contains(material)) {
            item.setItemStack(TheVoidKingdomUtils.clearVanillaStat(itemStack));
        }
    }
}

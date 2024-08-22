package flandre.scarlet.thevoidkingdom.functions.item.custom;

import com.molean.isletopia.framework.annotations.Bean;
import io.lumine.mythic.lib.api.item.NBTItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

@Bean
public class CustomItemDurabilityListener implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void on(PlayerItemDamageEvent event) {
        ItemStack itemStack = event.getItem();
        NBTItem nbtItem = NBTItem.get(itemStack);
        if (!nbtItem.hasType()) {
            return;
        }
        int damage = event.getDamage();
        switch (nbtItem.getType()) {
            case "HAMMER", "SWORD", "PICKAXE", "AXE", "SHOVEL", "HOE", "DAGGER" -> {
                if (damage == 2) {
                    event.setDamage(1);
                }
            }
        }
    }
}

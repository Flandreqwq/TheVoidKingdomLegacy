package flandre.scarlet.thevoidkingdom.functions.item.vanilla;

import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

@Bean
public class VanillaCraftListener implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void on(CraftItemEvent event) {
        CraftingInventory craftingInventory = event.getInventory();
        ItemStack result = craftingInventory.getResult();
        if (result == null) {
            return;
        }
        result = TheVoidKingdomUtils.checkClearVanillaStat(result);
        craftingInventory.setResult(result);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void on(PrepareItemCraftEvent event) {
        CraftingInventory craftingInventory = event.getInventory();
        ItemStack result = craftingInventory.getResult();
        if (result == null) {
            return;
        }
        result = TheVoidKingdomUtils.checkClearVanillaStat(result);
        craftingInventory.setResult(result);
    }
}

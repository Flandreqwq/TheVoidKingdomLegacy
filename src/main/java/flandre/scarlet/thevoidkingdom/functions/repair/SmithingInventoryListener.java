package flandre.scarlet.thevoidkingdom.functions.repair;

import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.SmithingInventory;

@Bean
public class SmithingInventoryListener implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void on(PrepareSmithingEvent event) {
        SmithingInventory smithingInventory = event.getInventory();
        ItemStack inputEquipment = smithingInventory.getInputEquipment();
        ItemStack inputMineral = smithingInventory.getInputMineral();
        ItemStack inputTemplate = smithingInventory.getInputTemplate();
        ItemStack resultItemStack = smithingInventory.getResult();
        if (resultItemStack == null) {
            return;
        }
        if (ItemsAdderUtils.isCustomItemStack(inputEquipment)) {
            event.setResult(null);
            return;
        }
        if (ItemsAdderUtils.isCustomItemStack(inputMineral)) {
            event.setResult(null);
            return;
        }
        if (ItemsAdderUtils.isCustomItemStack(inputTemplate)) {
            event.setResult(null);
            return;
        }
        TheVoidKingdomUtils.checkClearVanillaStat(resultItemStack);
        event.setResult(resultItemStack);
    }
}

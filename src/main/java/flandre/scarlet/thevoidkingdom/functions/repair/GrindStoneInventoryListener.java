package flandre.scarlet.thevoidkingdom.functions.repair;

import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareGrindstoneEvent;
import org.bukkit.inventory.GrindstoneInventory;
import org.bukkit.inventory.ItemStack;

@Bean
public class GrindStoneInventoryListener implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void on(PrepareGrindstoneEvent event) {
        GrindstoneInventory grindstoneInventory = event.getInventory();
        ItemStack upperItemStack = grindstoneInventory.getUpperItem();
        ItemStack lowerItemStack = grindstoneInventory.getLowerItem();
        ItemStack resultItemStack = grindstoneInventory.getResult();
        if (resultItemStack == null) {
            return;
        }
        if (ItemsAdderUtils.isCustomItemStack(upperItemStack)) {
            event.setResult(null);
            return;
        }
        if (ItemsAdderUtils.isCustomItemStack(lowerItemStack)) {
            event.setResult(null);
            return;
        }
        TheVoidKingdomUtils.checkClearVanillaStat(resultItemStack);
        event.setResult(resultItemStack);
    }
}

package flandre.scarlet.thevoidkingdom.functions.item.vanilla;

import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.inventory.ItemStack;

@Bean
public class VanillaItemSpawnListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void on(ItemSpawnEvent event) {
        if (event.isCancelled()) {
            return;
        }
        Item item = event.getEntity();
        ItemStack itemStack = item.getItemStack();
        TheVoidKingdomUtils.checkClearVanillaStat(itemStack);
    }
}

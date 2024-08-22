package flandre.scarlet.thevoidkingdom.functions.block.vanilla;

import com.molean.isletopia.framework.annotations.Bean;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.BrewEvent;

@Bean
public class DisableVanillaPotionBrewListener implements Listener {
    @EventHandler
    public void on(BrewEvent event) {
        event.setCancelled(true);
    }
}

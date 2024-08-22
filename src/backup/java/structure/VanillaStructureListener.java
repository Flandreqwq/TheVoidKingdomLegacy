package flandre.scarlet.thevoidkingdom.functions.structure;

import com.molean.isletopia.framework.annotations.Bean;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.AsyncStructureSpawnEvent;
import org.bukkit.generator.structure.StructureType;

@Bean
public class VanillaStructureListener implements Listener {
    @EventHandler(priority = EventPriority.NORMAL)
    public void on(AsyncStructureSpawnEvent event) {
        if (event.getWorld().getName().equals("world")) {
            if (event.getStructure().getStructureType().equals(StructureType.STRONGHOLD)) {
                event.setCancelled(true);
            }
        }
    }
}

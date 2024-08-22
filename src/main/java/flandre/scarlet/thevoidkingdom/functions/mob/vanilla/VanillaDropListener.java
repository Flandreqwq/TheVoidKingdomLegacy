package flandre.scarlet.thevoidkingdom.functions.mob.vanilla;

import com.molean.isletopia.framework.annotations.Bean;
import dev.lone.itemsadder.api.CustomStack;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

@Bean
public class VanillaDropListener implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void on(EntityDeathEvent event) {
        if (event.isCancelled()) {
            return;
        }
        Entity entity = event.getEntity();
        ActiveMob mythicMob = MythicBukkit.inst().getMobManager().getActiveMob(entity.getUniqueId()).orElse(null);
        if (mythicMob != null) {
            return;
        }
        EntityType entityType = event.getEntityType();
        switch (entityType) {
            case SHEEP, PIG, COW, MUSHROOM_COW, DONKEY, HORSE, MULE, RABBIT, CHICKEN, WOLF, LLAMA, CAMEL, GOAT, OCELOT, FOX, PANDA, POLAR_BEAR -> {
                Random random = new Random();
                CustomStack customStack = CustomStack.getInstance("vkmaterials:beast_blood");
                ItemStack itemStack = customStack.getItemStack();
                itemStack.setAmount(random.nextInt(3) + 1);
                event.getDrops().add(itemStack);
            }
        }
    }

}

package flandre.scarlet.thevoidkingdom.functions.item.vanilla;

import com.molean.isletopia.framework.annotations.Bean;
import io.lumine.mythic.lib.api.item.NBTItem;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

@Bean
public final class DisableVanillaBowListener implements Listener {
    @EventHandler(priority = EventPriority.LOW)
    public void on(EntityShootBowEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType() != EntityType.PLAYER) {
            return;
        }
        Player player = (Player) event.getEntity();
        ItemStack itemStack = event.getBow();
        if (itemStack == null) {
            return;
        }
        if (!itemStack.getType().equals(Material.BOW)) {
            return;
        }
        NBTItem nbtItem = NBTItem.get(itemStack);
        if (!nbtItem.hasType()) {
            event.setCancelled(true);
            player.sendMessage("§8[§d§l虚空之国§8]§c 一股神秘的力量导致这个物品异常脆弱");
            player.getWorld().spawnParticle(Particle.ITEM_CRACK, player.getLocation().add(0.0, 1.0, 0.0), 20, 0.2, 0.2, 0.2, 0.1, itemStack);
            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
            itemStack.setAmount(0);
        }
    }
}

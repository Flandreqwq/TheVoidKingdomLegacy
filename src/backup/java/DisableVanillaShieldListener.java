package flandre.scarlet.thevoidkingdom.functions.item.vanilla;

import com.molean.isletopia.framework.annotations.Bean;
import io.lumine.mythic.lib.api.item.NBTItem;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

@Bean
public final class DisableVanillaShieldListener implements Listener {
    @EventHandler(priority = EventPriority.LOW)
    public void on(EntityDamageByEntityEvent event) {
        if (event.getEntityType() != EntityType.PLAYER) {
            return;
        }
        Player player = (Player) event.getEntity();
        if (!player.isBlocking()) {
            return;
        }
        ItemStack itemInOffHand = player.getInventory().getItemInOffHand();
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        if (itemInOffHand.getType().equals(Material.SHIELD)) {
            NBTItem nbtItem = NBTItem.get(itemInOffHand);
            if (nbtItem.hasType()) {
                return;
            }
            player.sendMessage("§8[§d§l虚空之国§8]§c 一股神秘的力量导致这个物品异常脆弱");
            player.getWorld().spawnParticle(Particle.ITEM_CRACK, player.getLocation().add(0.0, 1.0, 0.0), 20, 0.2, 0.2, 0.2, 0.1, itemInOffHand);
            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
            itemInOffHand.setAmount(0);
        } else if (itemInMainHand.getType().equals(Material.SHIELD)) {
            NBTItem nbtItem = NBTItem.get(itemInMainHand);
            if (nbtItem.hasType()) {
                return;
            }
            player.sendMessage("§8[§d§l虚空之国§8]§c 一股神秘的力量导致这个物品异常脆弱");
            player.getWorld().spawnParticle(Particle.ITEM_CRACK, player.getLocation().add(0.0, 1.0, 0.0), 20, 0.2, 0.2, 0.2, 0.1, itemInMainHand);
            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
            itemInMainHand.setAmount(0);
        }
    }
}

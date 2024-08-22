package flandre.scarlet.thevoidkingdom.utils.game;

import com.molean.isletopia.framework.annotations.Bean;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Bean
public class PlayerOffHandInteract implements Listener {
    private static final Map<UUID, Integer> map = new HashMap<>();

    @EventHandler(priority = EventPriority.LOWEST)
    public void on(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (event.getHand() == EquipmentSlot.OFF_HAND) {
            System.out.println("now tick: " + Bukkit.getCurrentTick());
        }
        if (Bukkit.getCurrentTick() == map.getOrDefault(uuid, -1) && event.getHand() == EquipmentSlot.OFF_HAND) {
            System.out.println("cancel tick: " + Bukkit.getCurrentTick());
            event.setCancelled(true);
        }
    }

    public static void cancel(UUID uuid) {
        System.out.println("put cancel tick: " + Bukkit.getCurrentTick());
        map.put(uuid, Bukkit.getCurrentTick());
    }

    public static void cancel(Player player) {
        cancel(player.getUniqueId());
    }
}

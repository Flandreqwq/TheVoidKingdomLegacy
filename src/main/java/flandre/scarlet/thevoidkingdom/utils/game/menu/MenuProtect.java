package flandre.scarlet.thevoidkingdom.utils.game.menu;

import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.utils.framework.IntervalTask;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Bean
public class MenuProtect implements Listener {
    private static final Map<UUID, Menu> denyAllMap = new HashMap<>();

    @EventHandler(priority = EventPriority.LOW)
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        UUID uuid = player.getUniqueId();
        if (denyAllMap.containsKey(uuid)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void on(InventoryDragEvent event) {
        Player player = (Player) event.getWhoClicked();
        UUID uuid = player.getUniqueId();
        if (denyAllMap.containsKey(uuid)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void on(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();
        Player player = (Player) event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (!denyAllMap.containsKey(uuid)) {
            return;
        }
        InventoryCloseEvent.Reason reason = event.getReason();
        if (reason == InventoryCloseEvent.Reason.PLUGIN) {
            return;
        }
        if (inventory.getHolder() instanceof Menu.MenuHolder menuHolder) {
            if (reason == InventoryCloseEvent.Reason.OPEN_NEW) {
                menuHolder.getMenu().close(CloseReason.OPEN_OTHER);
            } else {
                menuHolder.getMenu().close(CloseReason.DEFAULT);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void on(EntityDamageEvent event) {
        if (event.isCancelled()) {
            return;
        }
        Entity entity = event.getEntity();
        if (!entity.getType().equals(EntityType.PLAYER)) {
            return;
        }
        Player player = (Player) entity;
        Inventory inventory = player.getOpenInventory().getTopInventory();
        if (inventory.getHolder() instanceof Menu.MenuHolder menuHolder) {
            menuHolder.getMenu().close(CloseReason.DAMAGED);
        }
    }

    @IntervalTask(value = 40, delay = 20, isAsynchronously = false)
    public void on() {
        Map<UUID, Menu> map = new HashMap<>(denyAllMap);
        map.forEach((uuid, menu) -> {
            if (menu instanceof BlockMenu blockMenu) {
                Player player = Bukkit.getPlayer(uuid);
                if (player != null && player.getLocation().distance(blockMenu.getBlockLocation().clone().add(0.5, 0.5, 0.5)) > 7) {
                    menu.close(CloseReason.TOO_FAR);
                }
            }
        });
    }


    public static void addDenyAll(Player player, Menu menu) {
        denyAllMap.put(player.getUniqueId(), menu);
    }


    public static boolean isDenyAll(Player player) {
        return denyAllMap.containsKey(player.getUniqueId());
    }


    public static void removeDenyAll(Player player) {
        denyAllMap.remove(player.getUniqueId());
    }

}

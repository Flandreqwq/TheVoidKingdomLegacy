package flandre.scarlet.thevoidkingdom.functions.mob.vanilla;

import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.utils.game.CoolDownMap;
import org.bukkit.Material;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

@Bean
public class PlayerMilkListener implements Listener {
    CoolDownMap<UUID> coolDownMap = new CoolDownMap<>(300000);

    private static final List<EntityType> milkEntities = List.of(EntityType.COW, EntityType.MUSHROOM_COW, EntityType.GOAT);

    @EventHandler(priority = EventPriority.HIGH)
    public void on(PlayerInteractEntityEvent event) {
        Entity entity = event.getRightClicked();
        if (!milkEntities.contains(entity.getType())) {
            return;
        }
        if (entity instanceof Ageable ageable) {
            if (!ageable.isAdult()) {
                return;
            }
        }
        Player player = event.getPlayer();
        ItemStack inputItemStack;
        EquipmentSlot equipmentSlot = event.getHand();
        if (equipmentSlot == EquipmentSlot.HAND) {
            inputItemStack = player.getInventory().getItemInMainHand();
        } else if (equipmentSlot == EquipmentSlot.OFF_HAND) {
            inputItemStack = player.getInventory().getItemInOffHand();
        } else {
            return;
        }
        if (!inputItemStack.getType().equals(Material.BUCKET)) {
            return;
        }
        UUID uuid = entity.getUniqueId();
        if (coolDownMap.isCoolDown(uuid)) {
            event.setCancelled(true);
        } else {
            coolDownMap.add(uuid);
        }
    }
}

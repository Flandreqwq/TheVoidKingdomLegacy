package flandre.scarlet.thevoidkingdom.functions.item.armor;

import flandre.scarlet.thevoidkingdom.utils.game.MMOItemsUtils;
import io.lumine.mythic.lib.api.item.NBTItem;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;
import java.util.Objects;
import java.util.Random;

//@Bean
public class ArmorDurabilityListener implements Listener {
    private final List<EntityDamageEvent.DamageCause> ignoreList = List.of(
            EntityDamageEvent.DamageCause.FIRE_TICK,
            EntityDamageEvent.DamageCause.SUFFOCATION,
            EntityDamageEvent.DamageCause.DROWNING,
            EntityDamageEvent.DamageCause.STARVATION,
            EntityDamageEvent.DamageCause.VOID,
            EntityDamageEvent.DamageCause.POISON,
            EntityDamageEvent.DamageCause.WITHER,
            EntityDamageEvent.DamageCause.MAGIC,
            EntityDamageEvent.DamageCause.FALL
    );

    @EventHandler(priority = EventPriority.HIGHEST)
    public void on(EntityDamageEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (!event.getEntityType().equals(EntityType.PLAYER)) {
            return;
        }
        if (ignoreList.contains(event.getCause())) {
            return;
        }
        Player player = (Player) event.getEntity();
        if (!player.getGameMode().equals(GameMode.SURVIVAL)) {
            return;
        }
        PlayerInventory inventory = player.getInventory();
        ItemStack oldHelmet = inventory.getHelmet();
        if (oldHelmet == null) {
            return;
        }
        if (!oldHelmet.getType().equals(Material.WARPED_FUNGUS_ON_A_STICK)) {
            return;
        }
        NBTItem nbtItem = NBTItem.get(oldHelmet);
        if (!nbtItem.hasType()) {
            return;
        }
        String type = nbtItem.getType();
        if (!type.contains("ARMOR_")) {
            return;
        }


        double durabilityLevel = oldHelmet.getEnchantmentLevel(Enchantment.DURABILITY);
        if (durabilityLevel > 0.0) {
            Random random = new Random();
            if (random.nextDouble() > 1.0 / (durabilityLevel + 1.0)) {
                return;
            }
        }



        int beforeDurability=MMOItemsUtils.getDurability(oldHelmet);
        int maxDurability = MMOItemsUtils.getMaxDurability(oldHelmet);
        if (beforeDurability == 0) {
            return;
        }
        if (beforeDurability == 1) {
            if (nbtItem.getBoolean("MMOITEMS_WILL_BREAK")) {
                inventory.setHelmet(null);
                player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
            } else {
                ItemStack newItem = Objects.requireNonNull(MMOItemsUtils.changeDurability(oldHelmet, 0, maxDurability));
                inventory.setHelmet(newItem);
            }
            return;
        }
        ItemStack newItem = Objects.requireNonNull(MMOItemsUtils.changeDurability(oldHelmet, beforeDurability - 1, maxDurability));
        inventory.setHelmet(newItem);
    }
}

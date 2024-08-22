package flandre.scarlet.thevoidkingdom.functions.item.vanilla;

import io.lumine.mythic.lib.api.item.NBTItem;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public final class DisableVanillaItemListener implements Listener {
    public static final List<Material> disableItems = List.of(
            Material.IRON_AXE,
            Material.IRON_PICKAXE,
            Material.IRON_HOE,
            Material.IRON_SHOVEL,
            Material.IRON_SWORD,
            Material.GOLDEN_AXE,
            Material.GOLDEN_PICKAXE,
            Material.GOLDEN_HOE,
            Material.GOLDEN_SHOVEL,
            Material.GOLDEN_SWORD,
            Material.DIAMOND_AXE,
            Material.DIAMOND_PICKAXE,
            Material.DIAMOND_HOE,
            Material.DIAMOND_SHOVEL,
            Material.DIAMOND_SWORD,
            Material.NETHERITE_AXE,
            Material.NETHERITE_PICKAXE,
            Material.NETHERITE_HOE,
            Material.NETHERITE_SHOVEL,
            Material.NETHERITE_SWORD
    );

    public static final List<Material> onlyDisableAttackItems = List.of(
            Material.WOODEN_AXE,
            Material.WOODEN_PICKAXE,
            Material.WOODEN_HOE,
            Material.WOODEN_SHOVEL,
            Material.WOODEN_SWORD,
            Material.STONE_AXE,
            Material.STONE_PICKAXE,
            Material.STONE_HOE,
            Material.STONE_SHOVEL,
            Material.STONE_SWORD
    );

    @EventHandler(priority = EventPriority.LOW)
    public void on(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!player.getGameMode().equals(GameMode.SURVIVAL)) {
            return;
        }
        ItemStack itemStack;
        if (event.getHand() == EquipmentSlot.OFF_HAND) {
            itemStack = player.getInventory().getItemInOffHand();
        } else if (event.getHand() == EquipmentSlot.HAND) {
            itemStack = player.getInventory().getItemInMainHand();
        } else {
            return;
        }
        Material type = itemStack.getType();
        if (type.isAir()) {
            return;
        }
        NBTItem nbtItem = NBTItem.get(itemStack);
        if (!nbtItem.hasType() && disableItems.contains(type)) {
            event.setCancelled(true);
            player.sendMessage("§8[§d§l虚空之国§8]§c 一股神秘的力量导致这个物品异常脆弱");
            player.getWorld().spawnParticle(Particle.ITEM_CRACK, player.getLocation().add(0.0, 1.0, 0.0), 20, 0.2, 0.2, 0.2, 0.1, itemStack);
            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
            itemStack.setAmount(0);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void on(EntityDamageByEntityEvent event) {
        if (event.isCancelled()) {
            return;
        }
        Entity entity = event.getDamager();
        if (entity.getType() != EntityType.PLAYER) {
            return;
        }
        Player player = (Player) entity;
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        Material type = itemStack.getType();
        if (type.isAir()) {
            return;
        }
        NBTItem nbtItem = NBTItem.get(itemStack);
        if (!nbtItem.hasType() && (disableItems.contains(type) || onlyDisableAttackItems.contains(type))) {
            event.setDamage(0.1);
        }
    }
}

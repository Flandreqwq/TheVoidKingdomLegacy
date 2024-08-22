package flandre.scarlet.thevoidkingdom.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class CauldronSpawner implements Listener {
    //生成炼金锅
    @EventHandler
    public void placeLianJinCauldron(PlayerInteractEvent event) {
        Block clickedBlock = event.getClickedBlock();
        Player player = event.getPlayer();
        if (event.getAction().isLeftClick()) {
            return;
        }
        if (clickedBlock == null) {
            return;
        }
        ItemStack item = event.getItem();
        if (item == null) {
            return;
        }
        if (!item.getItemMeta().getDisplayName().contains("§b掌中炼金锅")) {
            return;
        }
        if (!clickedBlock.getType().equals(Material.CAULDRON)) {
            player.sendMessage("§8[§d§l虚空之国§8]§c 右键炼药锅来转变成炼金锅");
            return;
        }
        if (!clickedBlock.getLocation().add(0, 1, 0).getBlock().getType().equals(Material.AIR)) {
            player.sendMessage("§8[§d§l虚空之国§8]§c 炼金锅上方被堵住会爆炸，请确保炼金锅上方无方块");
            return;
        }
        Collection<ArmorStand> collections = clickedBlock.getLocation().add(0.5, 0.1, 0.5).getNearbyEntitiesByType(ArmorStand.class, 1);
        for (ArmorStand armorStand : collections) {
            if (armorStand.getName().contains("炼金锅")) {
                return;
            }
        }
        @NotNull Entity[] entities = clickedBlock.getLocation().getChunk().getEntities();
        for (Entity entity : entities) {
            if (entity.getType().equals(EntityType.ARMOR_STAND)) {
                if (entity.getName().contains("炼金锅")) {
                    player.sendMessage("§8[§d§l虚空之国§8]§c 为维持炼金过程稳定，每个区块只能放置一个炼金锅哦");
                    return;
                }
            }
        }
        item.setAmount(item.getAmount() - 1);
        String world = clickedBlock.getWorld().getName();
        Location location = clickedBlock.getLocation();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mm mobs spawn 炼金锅-坐标矫正 1 " + world + "," + location.getX() + "," + location.getY() + "," + location.getZ());
    }
}

package flandre.scarlet.thevoidkingdom.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Random;

public class GetPlantMaterial implements Listener {
    //炼金植物材料获得
    @EventHandler(ignoreCancelled = true)
    public void materialPlantsGet(BlockBreakEvent event) {
        Block block = event.getBlock();
        Material material = block.getType();
        if (material != Material.WHEAT && material != Material.CARROTS && material != Material.POTATOES) {
            return;
        }
        Ageable age = (Ageable) block.getState().getBlockData();
        if (age.getAge() != 7) {
            return;
        }
        Random random = new Random();
        double rand = random.nextDouble();
        if (rand >= 0.2) {
            return;
        }
        String world = block.getWorld().getName();
        Location location = block.getLocation();
        if (material == Material.WHEAT) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mi drop MATERIAL_LIANJIN MOFAMAISUI " + world + " " + location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ() + " 1 1-1 0");
        } else if (material == Material.CARROTS) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mi drop MATERIAL_LIANJIN QIYILUOBOYE " + world + " " + location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ() + " 1 1-1 0");
        } else {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mi drop MATERIAL_LIANJIN YOUZHIFUWUQI " + world + " " + location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ() + " 1 1-1 0");
        }
    }
}

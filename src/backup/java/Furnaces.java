package flandre.scarlet.thevoidkingdom.functions.block.table;

import dev.lone.itemsadder.api.CustomBlock;
import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.*;
import org.bukkit.block.BlastFurnace;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Rotatable;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockCookEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;

public class Furnaces implements Listener {
    public ItemStack slag = null;

    public ItemStack GetSlag() {
        if (slag == null) {
            CustomStack customStack = CustomStack.getInstance("vkmaterials:luzha");
            if (customStack != null) {
                slag = customStack.getItemStack();
            }
        }
        return slag;
    }

    public void Produce(BlockCookEvent event, double possibility, double possibility2) {
        Random random = new Random();
        Block block = event.getBlock();
        World world = block.getWorld();
        Location location = block.getLocation();
        if (random.nextDouble() > possibility) {
            event.setCancelled(true);
            world.spawnParticle(Particle.SMOKE_NORMAL, location, 10, 0, 0.3, 0.3, 0.3);
            world.playSound(location, Sound.BLOCK_SMITHING_TABLE_USE, 10, 1);
        } else {
            if (random.nextDouble() < possibility2) {
                world.dropItem(location.clone().add(0, -0.7, 0), GetSlag());
            }
            world.spawnParticle(Particle.FLAME, location, 10, 0, 0.3, 0.3, 0.3);
            world.playSound(location, Sound.BLOCK_LAVA_POP, 10, 1);
        }
    }

    public boolean CheckStruckNorth(Location location1, Material frame, Material window) {
        Location location = location1.clone();
        if (!location.add(1, -1, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(-1, 0, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(-1, 0, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 1, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(2, 0, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 1, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(-1, 0, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(-1, 0, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, 1).getBlock().getType().equals(frame)) return false;
        if (!location.add(1, 0, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(1, 0, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, -1, 0).getBlock().getType().equals(window)) return false;
        if (!location.add(-2, 0, 0).getBlock().getType().equals(window)) return false;
        if (!location.add(0, -1, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(1, 0, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(1, 0, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, 1).getBlock().getType().equals(frame)) return false;
        if (!location.add(-1, 0, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(-1, 0, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 1, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(1, 0, 0).getBlock().getType().equals(window)) return false;
        if (!location.add(1, 0, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 1, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(-1, 0, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(-1, 0, 0).getBlock().getType().equals(frame)) return false;
        return true;
    }

    public boolean CheckStruckSouth(Location location1, Material frame, Material window) {
        Location location = location1.clone();
        if (!location.add(1, -1, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(-1, 0, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(-1, 0, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 1, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(2, 0, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 1, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(-1, 0, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(-1, 0, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, -1).getBlock().getType().equals(frame)) return false;
        if (!location.add(1, 0, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(1, 0, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, -1, 0).getBlock().getType().equals(window)) return false;
        if (!location.add(-2, 0, 0).getBlock().getType().equals(window)) return false;
        if (!location.add(0, -1, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(1, 0, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(1, 0, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, -1).getBlock().getType().equals(frame)) return false;
        if (!location.add(-1, 0, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(-1, 0, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 1, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(1, 0, 0).getBlock().getType().equals(window)) return false;
        if (!location.add(1, 0, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 1, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(-1, 0, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(-1, 0, 0).getBlock().getType().equals(frame)) return false;
        return true;
    }

    public boolean CheckStruckWest(Location location1, Material frame, Material window) {
        Location location = location1.clone();
        if (!location.add(0, -1, 1).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, -1).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, -1).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 1, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, 2).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 1, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, -1).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, -1).getBlock().getType().equals(frame)) return false;
        if (!location.add(1, 0, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, 1).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, 1).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, -1, 0).getBlock().getType().equals(window)) return false;
        if (!location.add(0, 0, -2).getBlock().getType().equals(window)) return false;
        if (!location.add(0, -1, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, 1).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, 1).getBlock().getType().equals(frame)) return false;
        if (!location.add(1, 0, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, -1).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, -1).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 1, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, 1).getBlock().getType().equals(window)) return false;
        if (!location.add(0, 0, 1).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 1, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, -1).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, -1).getBlock().getType().equals(frame)) return false;
        return true;
    }

    public boolean CheckStruckEast(Location location1, Material frame, Material window) {
        Location location = location1.clone();
        if (!location.add(0, -1, 1).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, -1).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, -1).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 1, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, 2).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 1, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, -1).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, -1).getBlock().getType().equals(frame)) return false;
        if (!location.add(-1, 0, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, 1).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, 1).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, -1, 0).getBlock().getType().equals(window)) return false;
        if (!location.add(0, 0, -2).getBlock().getType().equals(window)) return false;
        if (!location.add(0, -1, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, 1).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, 1).getBlock().getType().equals(frame)) return false;
        if (!location.add(-1, 0, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, -1).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, -1).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 1, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, 1).getBlock().getType().equals(window)) return false;
        if (!location.add(0, 0, 1).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 1, 0).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, -1).getBlock().getType().equals(frame)) return false;
        if (!location.add(0, 0, -1).getBlock().getType().equals(frame)) return false;
        return true;
    }

    public boolean CheckCore(Location location, String core) {
        Block block = location.getBlock();
        if (core.contains(":")) {
            CustomBlock customBlock = CustomBlock.byAlreadyPlaced(block);
            if (customBlock != null) {
                return customBlock.getNamespacedID().equals(core);
            } else {
                return false;
            }
        } else {
            return block.getType().equals(Material.getMaterial(core));
        }
    }

    public boolean CheckStruck(Block block, Material frame, Material window, String core) {
        Location location = block.getLocation();
        Directional directional = (Directional) block.getBlockData();
        switch (directional.getFacing()) {
            case EAST:
                return CheckStruckEast(location, frame, window) && CheckCore(location.clone().add(-1, 0, 0), core);
            case WEST:
                return CheckStruckWest(location, frame, window) && CheckCore(location.clone().add(1, 0, 0), core);
            case SOUTH:
                return CheckStruckSouth(location, frame, window) && CheckCore(location.clone().add(0, 0, -1), core);
            default:
                return CheckStruckNorth(location, frame, window) && CheckCore(location.clone().add(0, 0, 1), core);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void OnBlockCookEvent(BlockCookEvent event) {
        Block block = event.getBlock();
        if (!block.getType().equals(Material.BLAST_FURNACE)) {
            return;
        }
        ItemStack source = event.getSource();
        CustomStack customStack = CustomStack.byItemStack(source);
        String name;
        if (customStack == null) {
            name = source.getType().name();
        } else {
            name = customStack.getNamespacedID();
        }
        switch (name) {
            case "IRON_INGOT":
                if (CheckStruck(block, Material.STONE_BRICKS, Material.IRON_BARS, "LAVA")) {
                    Produce(event, 0.75, 0.5);
                } else if (CheckStruck(block, Material.DEEPSLATE_BRICKS, Material.IRON_BARS, "LAVA")) {
                    Produce(event, 1.0, 0.5);
                } else {
                    Produce(event, 0.5, 0.5);
                }
                break;
            case "vkmaterials:gangding":
                if (CheckStruck(block, Material.STONE_BRICKS, Material.IRON_BARS, "LAVA")) {
                    Produce(event, 0.3, 0.8);
                } else if (CheckStruck(block, Material.DEEPSLATE_BRICKS, Material.IRON_BARS, "LAVA")) {
                    Produce(event, 0.6, 0.8);
                } else {
                    Produce(event, 0.0, 0.8);
                }
        }
    }
}

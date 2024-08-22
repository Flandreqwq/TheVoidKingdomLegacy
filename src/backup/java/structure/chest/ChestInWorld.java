package flandre.scarlet.thevoidkingdom.functions.structure.chest;

import com.magmaguy.betterstructures.api.BuildPlaceEvent;
import com.magmaguy.betterstructures.api.ChestFillEvent;
import dev.lone.itemsadder.api.CustomStack;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Container;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public final class ChestInWorld implements Listener {
    private static final List<String> normalItems = List.of(
            "vkmaterials:xvjiejiejing", "ROTTEN_FLESH", "GUNPOWDER", "BONE", "STRING",
            "vkmaterials:xiaoshizi", "EXPERIENCE_BOTTLE", "BREAD", "TORCH", "STICK", "FLINT",
            "vkmaterials:xiding", "IRON_INGOT", "COPPER_INGOT", "GOLD_INGOT", "COAL", "AMETHYST_SHARD"
    );
    private static final List<String> rareItems = List.of(
            "vkmaterials:shenanchentu", "vkmaterials:bupi", "vkmaterials:cuzhipige", "vkmaterials:fukasuipian_1",
            "vkblocks:wooden_box", "vkblocks:bronze_box"
    );
    private static final List<String> treasureItems = List.of(
            "vkmaterials:xvjiejingjing", "vkmaterials:fukasuipian_2", "vkmaterials:kongbaifuka_1", "CAKE",
            "vkmaterials:jvwututengsuipian", "vkmaterials:linghuntutengsuipian", "vkmaterials:mofatutengsuipian",
            "vkblocks:locked_golden_box", "vkblocks:locked_void_box", "vkmaterials:tieyaoshi", "vkmaterials:jinyaoshi",
            "ENCHANTED_GOLDEN_APPLE"
    );
    private static final RewardTable chestInWorldRewardTable = new RewardTable(normalItems, rareItems, treasureItems,
            5, 15, 3,
            0, 3, 1,
            0.05, 1
    );


    @EventHandler(priority = EventPriority.LOW)
    public void OnChestFillEvent(ChestFillEvent event) {
        event.setCancelled(true);
        Container container = event.getContainer();
        if (!container.getWorld().getName().equals("world")) {
            return;
        }
        chestInWorldRewardTable.fillChest(container.getInventory());
    }

    public static void spawnChest(Location location) {
        Block block = location.getBlock();
        block.setType(Material.CHEST);
        Chest chest = (Chest) block.getState();
        chestInWorldRewardTable.fillChest(chest.getInventory());
    }

    public static void spawnChest(Chest chest) {
        chestInWorldRewardTable.fillChest(chest.getInventory());
    }

    public static void spawnChest(Inventory inventory) {
        chestInWorldRewardTable.fillChest(inventory);
    }
}

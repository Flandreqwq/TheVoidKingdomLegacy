package flandre.scarlet.thevoidkingdom.functions.block.custom;

import dev.lone.itemsadder.api.CustomBlock;
import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class TableCreateListener implements Listener {


    public boolean isSpecialCustomItem(ItemStack itemStack, String namespaceId) {
        CustomStack customStack = CustomStack.byItemStack(itemStack);
        if (customStack == null) {
            return false;
        }
        if (!Objects.equals(customStack.getNamespacedID(), namespaceId)) {
            return false;
        }
        return true;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void on(PlayerInteractEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (!event.getAction().isRightClick()) {
            return;
        }
        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }
        Block block = event.getClickedBlock();
        if (block == null) {
            return;
        }
        Player player = event.getPlayer();
        Location playerLocation = player.getLocation();
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        if (itemInMainHand.getType().isAir()) {
            return;
        }
        if (!player.isSneaking()) {
            return;
        }
        if (player.getWorld().getName().equals("world_spawn")) {
            return;
        }
        Material blockType = block.getType();
        Location location = block.getLocation();

        //虚界工作台
        if (blockType.equals(Material.CRAFTING_TABLE) && isSpecialCustomItem(itemInMainHand, "vkmaterials:xvjiejiejing")) {
            event.setCancelled(true);
            itemInMainHand.setAmount(itemInMainHand.getAmount() - 1);
            CustomBlock.place("vkblocks:void_crafting_table", location);
            player.playSound(playerLocation, Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 1, 0);
        }

        //虚界锻造台
        else if (blockType.equals(Material.SMITHING_TABLE) && isSpecialCustomItem(itemInMainHand, "vkmaterials:xvjiejiejing")) {
            event.setCancelled(true);
            itemInMainHand.setAmount(itemInMainHand.getAmount() - 1);
            CustomBlock.place("vkblocks:void_smithing_table", location);
            player.playSound(playerLocation, Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 1, 0);
        }
    }
}

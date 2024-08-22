package flandre.scarlet.thevoidkingdom.functions.block.custom;

import dev.lone.itemsadder.api.CustomBlock;
import dev.lone.itemsadder.api.CustomStack;
import dev.lone.itemsadder.api.Events.CustomBlockInteractEvent;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class BoxUnlockListener implements Listener {


    private void unlockBox(ItemStack itemStack, Location location, String namespaceId) {
        itemStack.setAmount(itemStack.getAmount() - 1);
        CustomBlock.place(namespaceId, location);
        location.getWorld().playSound(location, Sound.BLOCK_WOODEN_DOOR_OPEN, 1f, 1.5f);
    }

    @EventHandler
    public void on(CustomBlockInteractEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (!event.getAction().isRightClick()) {
            return;
        }
        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }
        Player player = event.getPlayer();
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        CustomStack customStack = CustomStack.byItemStack(itemInMainHand);
        if (customStack == null) {
            return;
        }
        String itemNamespaceId = customStack.getNamespacedID();
        String blockNamespaceId = event.getNamespacedID();
        Location location = event.getBlockClicked().getLocation();
        switch (blockNamespaceId) {
            case "vkblocks:locked_golden_box":
                if (itemNamespaceId.equals("vkmaterials:tieyaoshi")) {
                    unlockBox(itemInMainHand, location, "vkblocks:golden_box");
                }
                break;
            case "vkblocks:locked_void_box":
                if (itemNamespaceId.equals("vkmaterials:jinyaoshi")) {
                    unlockBox(itemInMainHand, location, "vkblocks:void_box");
                }
                break;
        }

    }
}

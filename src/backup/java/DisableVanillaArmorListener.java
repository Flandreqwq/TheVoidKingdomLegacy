package flandre.scarlet.thevoidkingdom.functions.item.armor;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import io.lumine.mythic.lib.api.item.NBTItem;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public final class DisableVanillaArmorListener implements Listener {
    @EventHandler
    public void on(PlayerArmorChangeEvent event) {
        ItemStack newItem = event.getNewItem();
        if (newItem == null) {
            return;
        }
        Material material = newItem.getType();
        if (material.equals(Material.TURTLE_HELMET)) {
            return;
        }
        String type = material.toString();
        if (!type.contains("HELMET") && !type.contains("CHESTPLATE") && !type.contains("LEGGINGS") && !type.contains("BOOTS")) {
            return;
        }
        NBTItem nbtItem=NBTItem.get(newItem);
        if (!nbtItem.hasType()) {
            Player player = event.getPlayer();
            PlayerArmorChangeEvent.SlotType slotType = event.getSlotType();
            PlayerInventory playerInventory = player.getInventory();
            if (slotType.equals(PlayerArmorChangeEvent.SlotType.HEAD)) {
                playerInventory.setHelmet(null);
            } else if (slotType.equals(PlayerArmorChangeEvent.SlotType.CHEST)) {
                playerInventory.setChestplate(null);
            } else if (slotType.equals(PlayerArmorChangeEvent.SlotType.LEGS)) {
                playerInventory.setLeggings(null);
            } else if (slotType.equals(PlayerArmorChangeEvent.SlotType.FEET)) {
                playerInventory.setBoots(null);
            }
            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
            player.sendMessage("§8[§d§l虚空之国§8]§c 这种材质的护甲中看不中用，一触即碎");
        }
    }
}

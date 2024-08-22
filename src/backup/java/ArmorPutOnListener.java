package flandre.scarlet.thevoidkingdom.functions.item.armor;

import io.lumine.mythic.lib.api.item.NBTItem;
import net.Indyuce.mmocore.api.player.PlayerData;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;

//@Bean
public class ArmorPutOnListener implements Listener {
    private final List<String> warriorJobs = List.of("战士");
    private final List<String> archerJobs = List.of("游侠");
    private final List<String> mageJobs = List.of("魔法使");

    public boolean isWarrior(PlayerData playerData) {
        String jobName = playerData.getProfess().getName();
        for (String job : warriorJobs) {
            if (jobName.equals(job)) {
                return true;
            }
        }
        return false;
    }

    public boolean isArcher(PlayerData playerData) {
        String jobName = playerData.getProfess().getName();
        for (String job : archerJobs) {
            if (jobName.equals(job)) {
                return true;
            }
        }
        return false;
    }

    public boolean isMage(PlayerData playerData) {
        String jobName = playerData.getProfess().getName();
        for (String job : mageJobs) {
            if (jobName.equals(job)) {
                return true;
            }
        }
        return false;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void on(PlayerInteractEvent event) {
        if (!event.getAction().isRightClick()) {
            return;
        }
        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }
        Player player = event.getPlayer();
        PlayerInventory playerInventory = player.getInventory();
        ItemStack itemStack = playerInventory.getItemInMainHand();
        if (!itemStack.getType().equals(Material.WARPED_FUNGUS_ON_A_STICK)) {
            return;
        }
        NBTItem nbtItem = NBTItem.get(itemStack);
        if (!nbtItem.hasType()) {
            return;
        }
        PlayerData playerData = PlayerData.get(player);
        double requiredLevel = nbtItem.getDouble("MMOITEMS_REQUIRED_LEVEL");
        double playerLevel = playerData.getLevel();
        if (requiredLevel > playerLevel) {
            return;
        }
        String itemType = nbtItem.getType();
        switch (itemType) {
            case "ARMOR_WARRIOR":
                if (!isWarrior(playerData)) {
                    return;
                }
                break;
            case "ARMOR_ARCHER":
                if (!isArcher(playerData)) {
                    return;
                }
                break;
            case "ARMOR_MAGE":
                if (!isMage(playerData)) {
                    return;
                }
                break;
            case "ARMOR":
            case "CLOTHES":
                break;
            default:
                return;
        }


        event.setCancelled(true);
        ItemStack oldHelmet = playerInventory.getHelmet();
        playerInventory.setHelmet(itemStack);
        playerInventory.setItemInMainHand(oldHelmet);
        player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void on(InventoryClickEvent event) {
        if (event.isCancelled()) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        if (!event.getSlotType().equals(InventoryType.SlotType.ARMOR)) {
            return;
        }
        if (event.getRawSlot() != 5) {
            return;
        }
        ItemStack itemStack = event.getCursor();
        if (itemStack == null) {
            return;
        }
        if (!itemStack.getType().equals(Material.WARPED_FUNGUS_ON_A_STICK)) {
            return;
        }
        NBTItem nbtItem = NBTItem.get(itemStack);
        if (!nbtItem.hasType()) {
            return;
        }
        PlayerData playerData = PlayerData.get(player);
        double requiredLevel = nbtItem.getDouble("MMOITEMS_REQUIRED_LEVEL");
        double playerLevel = playerData.getLevel();
        if (requiredLevel > playerLevel) {
            return;
        }
        String itemType = nbtItem.getType();
        switch (itemType) {
            case "ARMOR_WARRIOR":
                if (!isWarrior(playerData)) {
                    return;
                }
                break;
            case "ARMOR_ARCHER":
                if (!isArcher(playerData)) {
                    return;
                }
                break;
            case "ARMOR_MAGE":
                if (!isMage(playerData)) {
                    return;
                }
                break;
            case "ARMOR":
            case "CLOTHES":
                break;
            default:
                return;
        }


        event.setCancelled(true);
        PlayerInventory playerInventory = player.getInventory();
        ItemStack oldHelmet = playerInventory.getHelmet();
        playerInventory.setHelmet(itemStack);
        event.setCursor(oldHelmet);
    }
}

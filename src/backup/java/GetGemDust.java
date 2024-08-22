package flandre.scarlet.thevoidkingdom.listeners;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GetGemDust implements Listener {
    public boolean isItemLoreContain(ItemStack itemStack, String str) {
        List<String> itemLore = Objects.requireNonNullElse(itemStack.getItemMeta().getLore(), new ArrayList<String>());
        return itemLore.contains(str);
    }

    public boolean isItemNameContain(ItemStack itemStack, String str) {
        if (itemStack == null) {
            return false;
        }
        return itemStack.getItemMeta().getDisplayName().contains(str);
    }

    public int getGemType(ItemStack itemStack) {
        if (isItemNameContain(itemStack, "碎晶")) {
            return 0;
        } else if (isItemNameContain(itemStack, "纯净")) {
            return 2;
        } else if (isItemNameContain(itemStack, "优质")) {
            return 3;
        } else if (isItemNameContain(itemStack, "无瑕")) {
            return 4;
        } else if (isItemNameContain(itemStack, "意志")) {
            return -1;
        } else if (isItemNameContain(itemStack, "之力")) {
            return -2;
        } else {
            return 1;
        }
    }

    public String getGemColor(ItemStack itemStack) {
        if (isItemNameContain(itemStack, "日耀石")) {
            return "RED";
        } else if (isItemNameContain(itemStack, "碧玺石")) {
            return "GREEN";
        } else if (isItemNameContain(itemStack, "欧泊石")) {
            return "ORANGE";
        } else if (isItemNameContain(itemStack, "托帕石")) {
            return "YELLOW";
        } else if (isItemNameContain(itemStack, "蔷薇辉石")) {
            return "PINK";
        } else if (isItemNameContain(itemStack, "海蓝宝石")) {
            return "BLUE";
        } else if (isItemNameContain(itemStack, "堇青石")) {
            return "PURPLE";
        } else if (isItemNameContain(itemStack, "尖晶石")) {
            return "AQUA";
        }
        return "ERROR";
    }

    public void dropMMOItem(World world, Location location, String itemType, String itemName, int amountMin, int amountMax) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mi drop " + itemType + " " + itemName + " " + world.getName() + " " + location.getX() + " " + location.getY() + " " + location.getZ() + " 1 " + amountMin + "-" + amountMax + " 0");
    }

    @EventHandler
    public void OnEntityDamageEvent(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (event.getEntityType() != EntityType.DROPPED_ITEM) {
            return;
        }
        if (event.getCause() != EntityDamageEvent.DamageCause.ENTITY_EXPLOSION && event.getCause() != EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) {
            return;
        }
        Item item = (Item) entity;
        ItemStack itemStack = item.getItemStack();
        if (!isItemLoreContain(itemStack, "§c§l『宝石』")) {
            return;
        }
        int amount = itemStack.getAmount();
        String color = getGemColor(itemStack);
        Location location = item.getLocation();
        World world = item.getWorld();
        if (getGemType(itemStack) == 0) {
            item.remove();
            dropMMOItem(world, location, "MATERIAL", "DUST_" + color, amount, amount);
        } else if (getGemType(itemStack) == 1) {
            item.remove();
            dropMMOItem(world, location, "MATERIAL", "DUST_" + color, 8 * amount, 8 * amount);
        } else if (getGemType(itemStack) == 2) {
            item.remove();
            dropMMOItem(world, location, "MATERIAL", "DUST_" + color, 16 * amount, 16 * amount);
        } else if (getGemType(itemStack) == 3) {
            event.setCancelled(true);
        } else if (getGemType(itemStack) == 4) {
            event.setCancelled(true);
        } else if (getGemType(itemStack) == -1) {
            event.setCancelled(true);
        } else if (getGemType(itemStack) == -2) {
            event.setCancelled(true);
        }
    }
}

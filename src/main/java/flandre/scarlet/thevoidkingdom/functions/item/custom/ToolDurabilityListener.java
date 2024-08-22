package flandre.scarlet.thevoidkingdom.functions.item.custom;


import com.molean.isletopia.framework.annotations.Bean;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import flandre.scarlet.thevoidkingdom.utils.game.MMOItemsUtils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Bean
public class ToolDurabilityListener implements Listener {
    private static final List<String> checkTypes = List.of("PICKAXE", "AXE", "HOE", "SHOVEL", "DAGGER");

    @EventHandler(priority = EventPriority.LOW)
    public void on(PlayerItemDamageEvent event) {
        if (event.isCancelled()) {
            return;
        }
        Player player = event.getPlayer();
        if (player.getGameMode().equals(GameMode.CREATIVE)) {
            return;
        }
        ItemStack itemStack = event.getItem();
        NBTItem nbtItem = new NBTItem(itemStack);
        String type = nbtItem.getString("MMOITEMS_ITEM_TYPE");
        if (!checkTypes.contains(type)) {
            return;
        }
        int beforeDurability = MMOItemsUtils.getDurability(nbtItem);
        if (beforeDurability == -1 || event.getDamage() < beforeDurability) {
            return;
        }
        if (!nbtItem.getBoolean("MMOITEMS_WILL_BREAK")) {
            return;
        }


        NBTCompound nbtCompound = nbtItem.getCompound("itemsadder");
        nbtCompound.setInteger("custom_durability", 1);
        nbtCompound.setInteger("max_custom_durability", MMOItemsUtils.getMaxDurability(nbtItem));
        event.getItem().setItemMeta(nbtItem.getItem().getItemMeta());
    }
}

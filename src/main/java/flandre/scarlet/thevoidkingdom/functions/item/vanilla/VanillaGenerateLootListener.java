package flandre.scarlet.thevoidkingdom.functions.item.vanilla;

import com.molean.isletopia.framework.annotations.Bean;
import de.tr7zw.nbtapi.NBTItem;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@Bean
public class VanillaGenerateLootListener implements Listener {

    private void modifyLoot(List<ItemStack> lootList) {
        List<ItemStack> resultLootList = new ArrayList<>();
        for (ItemStack itemStack : lootList) {
            Material material = itemStack.getType();
            if (material.equals(Material.RABBIT_FOOT)) {
                NBTItem nbtItem = new NBTItem(itemStack);
                if (!nbtItem.hasKey("customLootTag")) {
                    resultLootList.add(itemStack);
                    continue;
                }
                String customLootTag = nbtItem.getString("customLootTag");
                ItemStack resultItemStack = ItemsAdderUtils.getItemStackNullable(customLootTag);
                if (resultItemStack == null) {
                    TheVoidKingdom.LOGGER.warning("战利品无法生成: " + customLootTag);
                    continue;
                }
                resultItemStack.setAmount(itemStack.getAmount());
                resultLootList.add(resultItemStack);
            } else {
                resultLootList.add(itemStack);
            }
        }
        lootList.clear();
        lootList.addAll(resultLootList);
    }


    @EventHandler(priority = EventPriority.HIGH)
    public void on(LootGenerateEvent event) {
        if (event.isCancelled()) {
            return;
        }
        List<ItemStack> lootList = event.getLoot();
        modifyLoot(lootList);
    }
}

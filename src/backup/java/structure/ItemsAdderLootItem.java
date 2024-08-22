package flandre.scarlet.thevoidkingdom.functions.structure;

import com.ryandw11.structure.loottables.ConfigLootItem;
import com.ryandw11.structure.loottables.LootTable;
import dev.lone.itemsadder.api.CustomStack;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

public class ItemsAdderLootItem extends ConfigLootItem {
    private ItemStack itemStack;

    public ItemsAdderLootItem(LootTable lootTable, String itemID, int weight, String amount) {
        super(lootTable, itemID, weight, amount);
    }

    @Override
    public void constructItem(ConfigurationSection configurationSection) {
        String namespaceId = configurationSection.getString("Material");
        CustomStack customStack = CustomStack.getInstance(namespaceId);
        if (customStack == null) {
            TheVoidKingdom.LOGGER.warning("不存在的ia物品: " + namespaceId);
            itemStack = null;
            return;
        }
        itemStack = customStack.getItemStack();
    }

    @Override
    public ItemStack getItemStack() {
        ItemStack cloneItem = itemStack.clone();
        itemStack.setAmount(getAmount());
        return cloneItem;
    }
}

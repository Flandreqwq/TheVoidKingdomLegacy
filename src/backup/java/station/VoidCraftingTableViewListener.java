package flandre.scarlet.thevoidkingdom.functions.station;

import com.molean.isletopia.framework.annotations.Bean;
import de.tr7zw.nbtapi.NBTItem;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.crafting.CraftingStation;
import net.Indyuce.mmoitems.api.crafting.Layout;
import net.Indyuce.mmoitems.api.crafting.recipe.CheckedRecipe;
import net.Indyuce.mmoitems.gui.CraftingStationView;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Bean
public class VoidCraftingTableViewListener implements Listener {
    @EventHandler(priority = EventPriority.LOW)
    public void on(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        if (inventory.getHolder() instanceof CraftingStationView craftingStationView) {
            CraftingStation craftingStation = craftingStationView.getStation();
            if (!craftingStation.getId().contains("void-crafting-table")) {
                return;
            }
            int slot = event.getRawSlot();
            if (slot < 0 || slot >= 54) {
                return;
            }
            ItemStack itemStack = inventory.getItem(slot);
            if (itemStack == null) {
                return;
            }
            if (itemStack.getType().isAir()) {
                return;
            }
            NBTItem nbtItem = new NBTItem(itemStack);
            if (!nbtItem.hasKey("stationId")) {
                return;
            }
            String stationId = nbtItem.getString("stationId");
            if (!MMOItems.plugin.getCrafting().hasStation(stationId)) {
                TheVoidKingdom.LOGGER.warning("不存在的工作站: " + stationId);
                return;
            }
            CraftingStation newCraftingStation = MMOItems.plugin.getCrafting().getStation(stationId);
            event.setCancelled(true);
            new CraftingStationView((Player) event.getWhoClicked(), newCraftingStation, 1).open();
        }

    }

    @EventHandler(priority = EventPriority.LOW)
    public void on(InventoryOpenEvent event) throws NoSuchFieldException, IllegalAccessException {
        Inventory inventory = event.getInventory();
        if (inventory.getHolder() instanceof CraftingStationView craftingStationView) {
            CraftingStation craftingStation = craftingStationView.getStation();
            if (!craftingStation.getId().contains("void-crafting-table")) {
                return;
            }
            Layout layout = craftingStation.getLayout();
            int previousSlot = layout.getRecipePreviousSlot();
            if (inventory.getItem(previousSlot) != null) {
                NBTItem nbtItem1 = new NBTItem(CraftingStationMenuIcon.VOID_CRAFTING_TABLE_LAST.getItemStack());
                nbtItem1.setString("ItemId", "PREVIOUS_PAGE");
                ItemStack previousItemStack = nbtItem1.getItem();
                inventory.setItem(previousSlot, previousItemStack);
            }
            int nextSlot = layout.getRecipeNextSlot();
            if (inventory.getItem(nextSlot) != null) {
                NBTItem nbtItem2 = new NBTItem(CraftingStationMenuIcon.VOID_CRAFTING_TABLE_NEXT.getItemStack());
                nbtItem2.setString("ItemId", "NEXT_PAGE");
                ItemStack nextItemStack = nbtItem2.getItem();
                inventory.setItem(layout.getRecipeNextSlot(), nextItemStack);
            }


            List<Integer> recipeSlots = layout.getRecipeSlots();
            Class clazz = craftingStationView.getClass();
            Field field = clazz.getDeclaredField("recipes");
            field.setAccessible(true);
            List<CheckedRecipe> checkedRecipes = new ArrayList<>((List<CheckedRecipe>) field.get(craftingStationView));
            List<CheckedRecipe> recipes = new ArrayList<>();
            Iterator<CheckedRecipe> iterator = checkedRecipes.iterator();
            while (iterator.hasNext()) {
                CheckedRecipe checkedRecipe = iterator.next();
                if (checkedRecipe.allIngredientsHad() && checkedRecipe.areConditionsMet()) {
                    recipes.add(checkedRecipe);
                    iterator.remove();
                }
            }
            recipes.addAll(checkedRecipes);
            Class clazz2 = craftingStationView.getClass().getSuperclass();
            Field field2 = clazz2.getDeclaredField("page");
            field2.setAccessible(true);
            int page = (int) field2.get(craftingStationView);
            int min = (page - 1) * recipeSlots.size();
            int max = page * recipeSlots.size();
            for (int j = min; j < max; ++j) {
                if (j >= recipes.size()) {
                    if (craftingStation.getItemOptions().hasNoRecipe()) {
                        inventory.setItem(layout.getRecipeSlots().get(j - min), craftingStation.getItemOptions().getNoRecipe());
                    }
                } else {
                    inventory.setItem(layout.getRecipeSlots().get(j - min), (recipes.get(j)).display());
                }
            }


            if (event.getPlayer().hasPermission("vk.item.advanced_guidebook")) {
                inventory.setItem(1, CraftingStationMenuIcon.getTypeIcon("vkmaterials:xvkongzhiguoshouce_2", "<!italic><bold><gold>全部", "void-crafting-table"));
            } else {
                inventory.setItem(1, CraftingStationMenuIcon.getTypeIcon("vkmaterials:xvkongzhiguoshouce_1", "<!italic><bold><gold>全部", "void-crafting-table"));
            }
            inventory.setItem(2, CraftingStationMenuIcon.getTypeIcon("vkblocks:bronze_block", "<!italic><bold><gold>方块", "void-crafting-table-block"));
            inventory.setItem(3, CraftingStationMenuIcon.getTypeIcon("vkmaterials:mojingfen", "<!italic><bold><gold>材料", "void-crafting-table-material"));
            inventory.setItem(4, CraftingStationMenuIcon.getTypeIcon("vktools:pickaxe_xxx_qingtong", "<!italic><bold><gold>工具", "void-crafting-table-tool"));
            inventory.setItem(5, CraftingStationMenuIcon.getTypeIcon("vkweapons:zijing_wand", "<!italic><bold><gold>武器", "void-crafting-table-weapon"));
            inventory.setItem(6, CraftingStationMenuIcon.getTypeIcon("vkarmors:zijing_chestplate", "<!italic><bold><gold>护甲", "void-crafting-table-armor"));
            inventory.setItem(7, CraftingStationMenuIcon.getTypeIcon("vkaccessories:jinzhihufu", "<!italic><bold><gold>饰品", "void-crafting-table-accessory"));
        }
    }
}

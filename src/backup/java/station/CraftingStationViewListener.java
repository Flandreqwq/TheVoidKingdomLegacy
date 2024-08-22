package flandre.scarlet.thevoidkingdom.functions.station;

import de.tr7zw.nbtapi.NBTItem;
import net.Indyuce.mmoitems.api.crafting.CraftingStation;
import net.Indyuce.mmoitems.api.crafting.Layout;
import net.Indyuce.mmoitems.api.crafting.recipe.CheckedRecipe;
import net.Indyuce.mmoitems.gui.CraftingStationView;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//@Bean
public class CraftingStationViewListener implements Listener {
    @EventHandler(priority = EventPriority.LOW)
    public void on(InventoryOpenEvent event) throws NoSuchFieldException, IllegalAccessException {
        Inventory inventory = event.getInventory();
        if (inventory.getHolder() instanceof CraftingStationView craftingStationView) {
            CraftingStation craftingStation = craftingStationView.getStation();
            Layout layout = craftingStation.getLayout();
            ItemStack previousIcon;
            ItemStack nextIcon;
            switch (craftingStation.getId()) {
                case "void-crafting-table" -> {
                    previousIcon = CraftingStationMenuIcon.VOID_CRAFTING_TABLE_LAST.getItemStack().clone();
                    nextIcon = CraftingStationMenuIcon.VOID_CRAFTING_TABLE_NEXT.getItemStack().clone();
                }
                default -> {
                    return;
                }
            }
            int previousSlot = layout.getRecipePreviousSlot();
            if (inventory.getItem(previousSlot) != null) {
                NBTItem nbtItem1 = new NBTItem(previousIcon);
                nbtItem1.setString("ItemId", "PREVIOUS_PAGE");
                ItemStack previousItemStack = nbtItem1.getItem();
                inventory.setItem(previousSlot, previousItemStack);
            }
            int nextSlot = layout.getRecipeNextSlot();
            if (inventory.getItem(nextSlot) != null) {
                NBTItem nbtItem2 = new NBTItem(nextIcon);
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
        }
    }
}

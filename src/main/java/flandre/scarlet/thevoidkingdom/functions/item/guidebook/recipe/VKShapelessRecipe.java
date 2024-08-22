package flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe;

import flandre.scarlet.thevoidkingdom.functions.item.guidebook.RecipeListMenu;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.SwitchedItem;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.SwitchedItemManager;
import flandre.scarlet.thevoidkingdom.utils.framework.register.Register;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.Iterator;
import java.util.List;

@Register(registerName = "无序合成配方")
public record VKShapelessRecipe(ShapelessRecipe shapelessRecipe) implements VKRecipe {
    private static final List<Integer> SLOTS = List.of(10, 11, 12, 19, 20, 21, 28, 29, 30);

    @Override
    public String getMenuImagePlaceholder() {
        return ":gui_guide_crafting_table:";
    }

    @Override
    public String getMenuDisplayName() {
        return "工作台（无序合成）";
    }

    @Override
    public Inventory generateInventory(RecipeListMenu.RecipeListMenuHolder holder) {
        Inventory inventory = Bukkit.createInventory(holder, 54, Component.text(VKRecipe.generateTitle(this)));
        List<RecipeChoice> recipeChoiceList = shapelessRecipe.getChoiceList();
        Iterator<Integer> iterator = SLOTS.iterator();
        for (RecipeChoice recipeChoice : recipeChoiceList) {
            SwitchedItemManager.create(inventory,new SwitchedItem(VKRecipe.getBukkitRecipeChoiceItemStacks(recipeChoice), SwitchedItem.SwitchMode.NORMAL,iterator.next()));
        }
        ItemStack resultItemStack = shapelessRecipe.getResult();
        inventory.setItem(25, resultItemStack);
        return inventory;
    }

    @Override
    public boolean checkInput(String namespaceId) {
        for (RecipeChoice recipeChoice : shapelessRecipe.getChoiceList()) {
            if (VKRecipe.checkChoice(recipeChoice, namespaceId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkResult(String namespaceId) {
        return VKRecipe.checkItemStack(shapelessRecipe.getResult(), namespaceId);
    }


    @Override
    public boolean isValid() {
        return true;
    }
}

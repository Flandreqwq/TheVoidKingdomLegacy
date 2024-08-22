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
import org.bukkit.inventory.StonecuttingRecipe;

@Register(registerName = "切石机配方")
public record VKStoneCuttingRecipe(StonecuttingRecipe stonecuttingRecipe) implements VKRecipe {

    @Override
    public String getMenuImagePlaceholder() {
        return ":gui_guide_stonecutter:";
    }

    @Override
    public String getMenuDisplayName() {
        return "切石机";
    }

    @Override
    public Inventory generateInventory(RecipeListMenu.RecipeListMenuHolder holder) {
        Inventory inventory = Bukkit.createInventory(holder, 54, Component.text(VKRecipe.generateTitle(this)));
        RecipeChoice recipeChoice = stonecuttingRecipe.getInputChoice();
        SwitchedItemManager.create(inventory,new SwitchedItem(VKRecipe.getBukkitRecipeChoiceItemStacks(recipeChoice), SwitchedItem.SwitchMode.NORMAL,20));
        ItemStack resultItemStack = stonecuttingRecipe.getResult();
        inventory.setItem(24, resultItemStack);
        return inventory;
    }

    @Override
    public boolean checkInput(String namespaceId) {
        return VKRecipe.checkChoice(stonecuttingRecipe.getInputChoice(), namespaceId);
    }

    @Override
    public boolean checkResult(String namespaceId) {
        return VKRecipe.checkItemStack(stonecuttingRecipe.getResult(), namespaceId);
    }


    @Override
    public boolean isValid() {
        return true;
    }
}

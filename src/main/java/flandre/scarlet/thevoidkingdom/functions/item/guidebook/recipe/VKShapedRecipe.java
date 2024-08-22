package flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe;

import flandre.scarlet.thevoidkingdom.functions.item.guidebook.RecipeListMenu;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.SwitchedItem;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.SwitchedItemManager;
import flandre.scarlet.thevoidkingdom.utils.framework.register.Register;
import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import java.util.List;
import java.util.Map;

@Register(registerName = "有序合成配方")
public record VKShapedRecipe(ShapedRecipe shapedRecipe) implements VKRecipe {
    private static final List<Integer> SLOTS = List.of(10, 11, 12);

    @Override
    public String getMenuImagePlaceholder() {
        return ":gui_guide_crafting_table:";
    }

    @Override
    public String getMenuDisplayName() {
        return "工作台（有序合成）";
    }

    @Override
    public Inventory generateInventory(RecipeListMenu.RecipeListMenuHolder holder) {
        Inventory inventory = Bukkit.createInventory(holder, 54, Component.text(VKRecipe.generateTitle(this)));
        String[] shape = shapedRecipe.getShape();
        Map<Character, RecipeChoice> recipeChoiceMap = shapedRecipe.getChoiceMap();
        int j = 0;
        for (String line : shape) {
            int i = 0;
            char[] chars = line.toCharArray();
            for (char c : chars) {
                if (c == ' ') {
                    continue;
                }
                RecipeChoice recipeChoice = recipeChoiceMap.get(c);
                if (recipeChoice != null) {
                    SwitchedItemManager.create(inventory,new SwitchedItem(VKRecipe.getBukkitRecipeChoiceItemStacks(recipeChoice), SwitchedItem.SwitchMode.NORMAL,SLOTS.get(i) + j * 9));
                }
                i++;
            }
            j++;
        }
        ItemStack resultItemStack = shapedRecipe.getResult();
        inventory.setItem(25, TheVoidKingdomUtils.checkClearVanillaStat(resultItemStack));
        return inventory;
    }

    @Override
    public boolean checkInput(String namespaceId) {
        for (RecipeChoice recipeChoice : shapedRecipe.getChoiceMap().values()) {
            if (VKRecipe.checkChoice(recipeChoice, namespaceId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkResult(String namespaceId) {
        return VKRecipe.checkItemStack(shapedRecipe.getResult(), namespaceId);
    }


    @Override
    public boolean isValid() {
        return true;
    }
}

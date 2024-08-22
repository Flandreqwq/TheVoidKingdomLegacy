package flandre.scarlet.thevoidkingdom.functions.item.guidebook;

import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class ShapedRecipeMenu extends RecipeMenu {
    private final ShapedRecipe shapedRecipe;
    private static final List<Integer> SLOTS = List.of(10, 11, 12);

    public ShapedRecipeMenu(ShapedRecipe shapedRecipe, RecipeListMenu.RecipeListMenuHolder holder) {
        super();
        this.shapedRecipe = shapedRecipe;
        String tableName = "工作台（有序合成）";
        int offset = 33 + (127 + 9 * tableName.length()) / 2;
        this.title = ":offset_-16::gui_guide_crafting_table::offset_-" + offset + ":" + tableName;
        this.inventory = getInventory(title, shapedRecipe, holder);
    }

    public Inventory getInventory(String title, ShapedRecipe shapedRecipe, RecipeListMenu.RecipeListMenuHolder holder) {
        Inventory inventory = Bukkit.createInventory(holder, 54, Component.text(title));
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
                    if (recipeChoice instanceof RecipeChoice.ExactChoice exactChoice) {
                        ItemStack itemStack = exactChoice.getItemStack();
                        inventory.setItem(SLOTS.get(i) + j * 9, itemStack);
                    } else if (recipeChoice instanceof RecipeChoice.MaterialChoice materialChoice) {
                        List<Material> list = materialChoice.getChoices();
                        Random random = new Random();
                        ItemStack itemStack = new ItemStack(list.get(random.nextInt(list.size())));
                        inventory.setItem(SLOTS.get(i) + j * 9, itemStack);
                    }
                }
                i++;
            }
            j++;
        }
        ItemStack resultItemStack = shapedRecipe.getResult();
        inventory.setItem(25, TheVoidKingdomUtils.checkClearVanillaStat(resultItemStack));
        return inventory;
    }

    public ShapedRecipe getShapedRecipe() {
        return shapedRecipe;
    }
}

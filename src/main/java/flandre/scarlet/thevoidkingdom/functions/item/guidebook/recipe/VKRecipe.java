package flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe;

import dev.lone.itemsadder.api.CustomStack;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.RecipeListMenu;
import flandre.scarlet.thevoidkingdom.utils.framework.register.Registrable;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public interface VKRecipe extends Registrable {
    static String generateTitle(VKRecipe vkRecipe) {
        String menuDisplayName = vkRecipe.getMenuDisplayName();
        int offset = 33 + (127 + 9 * menuDisplayName.length()) / 2;
        return ":offset_-16:" + vkRecipe.getMenuImagePlaceholder() + ":offset_-" + offset + ":" + menuDisplayName;
    }

    static boolean checkChoice(RecipeChoice recipeChoice, String namespaceId) {
        if (namespaceId.contains(":")) {
            if (recipeChoice instanceof RecipeChoice.ExactChoice exactChoice) {
                List<ItemStack> itemStacks = exactChoice.getChoices();
                for (ItemStack itemStack : itemStacks) {
                    if (ItemsAdderUtils.getItemNamespaceId(itemStack).equals(namespaceId)) {
                        return true;
                    }
                }
            }
        } else {
            Material material = Material.getMaterial(namespaceId);
            if (recipeChoice instanceof RecipeChoice.ExactChoice exactChoice) {
                List<ItemStack> itemStacks = exactChoice.getChoices();
                for (ItemStack itemStack : itemStacks) {
                    CustomStack customStack = CustomStack.byItemStack(itemStack);
                    if (customStack == null && itemStack.getType().equals(material)) {
                        return true;
                    }
                }
            } else if (recipeChoice instanceof RecipeChoice.MaterialChoice materialChoice) {
                return materialChoice.getChoices().contains(material);
            }
        }
        return false;
    }

    static boolean checkItemStack(ItemStack itemStack, String namespaceId) {
        if (namespaceId.contains(":")) {
            CustomStack customStack = CustomStack.byItemStack(itemStack);
            if (customStack == null) {
                return false;
            } else {
                return customStack.getNamespacedID().equals(namespaceId);
            }
        } else {
            Material material = Material.getMaterial(namespaceId);
            if (material == null) {
                return false;
            } else {
                return CustomStack.byItemStack(itemStack) == null && itemStack.getType().equals(material);
            }
        }
    }

    static List<ItemStack> getBukkitRecipeChoiceItemStacks(RecipeChoice recipeChoice) {
        List<ItemStack> itemStacks = new ArrayList<>();
        if (recipeChoice instanceof RecipeChoice.ExactChoice exactChoice) {
            itemStacks = exactChoice.getChoices();
        } else if (recipeChoice instanceof RecipeChoice.MaterialChoice materialChoice) {
            List<Material> list = materialChoice.getChoices();
            for (Material material : list) {
                ItemStack itemStack = new ItemStack(material);
                ItemMeta itemMeta = itemStack.getItemMeta();
                if (itemMeta instanceof Damageable damageable) {
                    damageable.setDamage(0);
                    itemStack.setItemMeta(damageable);
                }
                itemStacks.add(TheVoidKingdomUtils.checkClearVanillaStat(itemStack));
            }
        }
        return itemStacks;
    }

    String getMenuImagePlaceholder();

    String getMenuDisplayName();

    Inventory generateInventory(RecipeListMenu.RecipeListMenuHolder holder);

    boolean checkInput(String namespaceId);

    boolean checkResult(String namespaceId);

}

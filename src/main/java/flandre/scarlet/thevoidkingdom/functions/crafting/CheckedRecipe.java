package flandre.scarlet.thevoidkingdom.functions.crafting;

import de.tr7zw.nbtapi.NBTItem;
import dev.lone.itemsadder.api.CustomStack;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe.VoidCraftingTableRecipe;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import flandre.scarlet.thevoidkingdom.utils.game.LangUtils;
import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class CheckedRecipe {
    private final VoidCraftingTableRecipe voidCraftingTableRecipe;
    private final Map<String, Integer> leftIngredients;

    public CheckedRecipe(Map<String, Integer> playerIngredients, VoidCraftingTableRecipe voidCraftingTableRecipe) {
        this.voidCraftingTableRecipe = voidCraftingTableRecipe;
        Map<String, Integer> mapClone = new HashMap<>(voidCraftingTableRecipe.ingredients());
        for (Map.Entry<String, Integer> playerIngredientsEntry : playerIngredients.entrySet()) {
            Iterator<Map.Entry<String, Integer>> iterator = mapClone.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Integer> recipeIndgredientsEntry = iterator.next();
                String requireNamespaceId = recipeIndgredientsEntry.getKey();
                int requireAmount = recipeIndgredientsEntry.getValue();
                if (playerIngredientsEntry.getKey().equals(requireNamespaceId)) {
                    if (playerIngredientsEntry.getValue() >= requireAmount) {
                        iterator.remove();
                    } else {
                        recipeIndgredientsEntry.setValue(requireAmount - playerIngredientsEntry.getValue());
                    }
                    break;
                }
            }
        }
        leftIngredients = mapClone;
    }

    public boolean isIngredientMeet() {
        return leftIngredients.isEmpty();
    }

    public ItemStack getDisplayItemStack() {
        ItemStack displayItemStack;
        String resultString = voidCraftingTableRecipe.resultString();
        if (resultString.contains("#")) {
            String[] strings = resultString.split("#");
            displayItemStack = ItemsAdderUtils.getItemStackNotNull(strings[0], strings[1]);
        } else {
            displayItemStack = ItemsAdderUtils.getItemStackNoCheck(resultString);
        }
        displayItemStack.setAmount(voidCraftingTableRecipe.resultAmount());
        List<String> lores = new ArrayList<>();
        lores.add("<!italic><white>%img_long_line_1%");
        lores.add("<!italic><white> %img_info% <gold>合成所需材料:");
        voidCraftingTableRecipe.ingredients().forEach((namespaceId, amount) -> {
            String displayName;
            if (namespaceId.contains(":")) {
                displayName = CustomStack.getInstance(namespaceId).getDisplayName().replace("§f", "");
            } else {
                displayName = LangUtils.get(Objects.requireNonNull(Material.getMaterial(namespaceId)).translationKey());
            }
            if (leftIngredients.containsKey(namespaceId)) {
                lores.add("<!italic><red> ✖ <white>" + displayName + " <gray>*" + amount);
            } else {
                lores.add("<!italic><dark_gray> ✔ <white>" + displayName + " <gray>*" + amount);
            }
        });
        lores.add("<!italic><white>%img_long_line_1%");
        lores.add("<!italic><white> %img_info% <gold>左键点击 <dark_gray>>> <gold>合成一次");
        lores.add("<!italic><white> %img_info% <gold>右键点击 <dark_gray>>> <gold>预览配方");
        lores.add("<!italic><white> %img_info% <gold>Shift+左键点击 <dark_gray>>> <gold>合成全部");
        lores.add("<!italic><white>%img_long_line_1%");
        displayItemStack.lore(TheVoidKingdomUtils.deserializeList(lores, true));
        NBTItem nbtItem = new NBTItem(displayItemStack);
        nbtItem.setString("recipeKey", voidCraftingTableRecipe.key());
        return nbtItem.getItem();
    }

    public VoidCraftingTableRecipe getVoidCraftingTableRecipe() {
        return voidCraftingTableRecipe;
    }

    public Map<String, Integer> getLeftIngredients() {
        return leftIngredients;
    }
}

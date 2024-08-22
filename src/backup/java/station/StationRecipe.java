package flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe.voidcarftingtable;

import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.RecipeListMenu;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe.VKRecipe;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import flandre.scarlet.thevoidkingdom.utils.game.MMOItemsUtils;
import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import io.lumine.mythic.lib.api.item.NBTItem;
import net.Indyuce.mmoitems.api.Type;
import net.Indyuce.mmoitems.api.crafting.CraftingStation;
import net.Indyuce.mmoitems.api.crafting.ingredient.Ingredient;
import net.Indyuce.mmoitems.api.crafting.ingredient.MMOItemIngredient;
import net.Indyuce.mmoitems.api.crafting.ingredient.VanillaIngredient;
import net.Indyuce.mmoitems.api.crafting.recipe.CraftingRecipe;
import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public record StationRecipe(CraftingStation station, CraftingRecipe craftingRecipe) implements VKRecipe {
    private static final List<Integer> SLOTS = List.of(10, 11, 12, 13, 19, 20, 21, 22, 28, 29, 30, 31);

    @Override
    public String getMenuImagePlaceholder() {
        return ":gui_guide_" + station.getId().replace('-', '_') + ":";
    }

    @Override
    public String getMenuDisplayName() {
        return switch (station.getId()) {
            case "void-crafting-table" -> "虚界工作台";
            case "spellcard-table-1" -> "虚界符卡台";
            default -> "Error: Unknow Station";
        };
    }

    @Override
    public Inventory generateInventory(RecipeListMenu.RecipeListMenuHolder holder) {
        Inventory inventory = Bukkit.createInventory(holder, 54, Component.text(VKRecipe.generateTitle(this)));
        Iterator<Integer> iterator = SLOTS.iterator();
        for (Ingredient<?> ingredient : craftingRecipe.getIngredients()) {
            ItemStack itemStack;
            if (ingredient instanceof MMOItemIngredient mmoItemIngredient) {
                MMOItemTemplate mmoItemTemplate = mmoItemIngredient.getTemplate();
                Type miType = mmoItemTemplate.getType();
                String miName = mmoItemTemplate.getId();
                itemStack = ItemsAdderUtils.getItemStackByMiTypeName(miType.getId(), miName);
            } else if (ingredient instanceof VanillaIngredient vanillaIngredient) {
                Material material = vanillaIngredient.getMaterial();
                itemStack = TheVoidKingdomUtils.checkClearVanillaStat(new ItemStack(material));
            } else {
                TheVoidKingdom.LOGGER.warning("未知的工作站配方材料类型");
                continue;
            }
            int amount = ingredient.getAmount();
            while (amount > 0) {
                if (amount > 64) {
                    itemStack.setAmount(64);
                    amount -= 64;
                } else {
                    itemStack.setAmount(amount);
                    amount -= amount;
                }
                if (iterator.hasNext()) {
                    inventory.setItem(iterator.next(), itemStack);
                } else {
                    TheVoidKingdom.LOGGER.warning("工作站配方所需物品过多，无法完全显示");
                    break;
                }
            }
        }
        String resultNamespaceId = getStationCraftRecipeNamespaceId(craftingRecipe);
        ItemStack resultItemStack;
        if (resultNamespaceId != null) {
            resultItemStack = ItemsAdderUtils.getItemStackNoCheck(resultNamespaceId);
        } else {
            resultItemStack = ItemsAdderUtils.getLostTextureIcon("<red><italic>Error: 无法将mi物品映射成ia物品");
        }
        resultItemStack.setAmount(craftingRecipe.getOutputAmount());
        inventory.setItem(25, resultItemStack);
        return inventory;
    }

    @Override
    public boolean checkInput(String namespaceId) {
        for (Ingredient<?> ingredient : craftingRecipe.getIngredients()) {
            if (ingredient instanceof MMOItemIngredient mmoItemIngredient) {
                MMOItemTemplate mmoItemTemplate = mmoItemIngredient.getTemplate();
                Type miType = mmoItemTemplate.getType();
                String miName = mmoItemTemplate.getId();
                if (Objects.equals(namespaceId, MMOItemsUtils.getIANamespace(miType.getId()) + ":" + miName.toLowerCase())) {
                    return true;
                }
            } else if (ingredient instanceof VanillaIngredient vanillaIngredient) {
                Material material = vanillaIngredient.getMaterial();
                if (material.toString().equals(namespaceId)) {
                    return true;
                }
            } else {
                TheVoidKingdom.LOGGER.warning("未知的工作站配方材料类型");
            }
        }
        return false;
    }

    @Override
    public boolean checkResult(String namespaceId) {
        String resultNamespaceId = getStationCraftRecipeNamespaceId(craftingRecipe);
        return Objects.equals(resultNamespaceId, namespaceId);
    }

    @Override
    public String getRegisterName() {
        return "自定义工作站配方";
    }

    @Override
    public boolean isValid() {
        return true;
    }

    private String getStationCraftRecipeNamespaceId(CraftingRecipe craftingRecipe) {
        ItemStack resultItemStack = craftingRecipe.getOutputItemStack(null);
        NBTItem nbtItem = NBTItem.get(resultItemStack);
        String miType = nbtItem.getType();
        String miName = nbtItem.getString("MMOITEMS_ITEM_ID");
        String namespaceId = MMOItemsUtils.getIANamespace(miType) + ":" + miName.toLowerCase();
        return ItemsAdderUtils.isItemNamespaceIdExist(namespaceId) ? namespaceId : null;
    }
}

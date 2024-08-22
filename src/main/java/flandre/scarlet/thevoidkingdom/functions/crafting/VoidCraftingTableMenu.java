package flandre.scarlet.thevoidkingdom.functions.crafting;

import flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe.VoidCraftingTableRecipe;
import flandre.scarlet.thevoidkingdom.utils.framework.register.RegisterManager;
import flandre.scarlet.thevoidkingdom.utils.game.*;
import flandre.scarlet.thevoidkingdom.utils.game.menu.BlockMenu;
import flandre.scarlet.thevoidkingdom.utils.game.menu.CloseReason;
import flandre.scarlet.thevoidkingdom.utils.game.menu.MenuProtect;
import flandre.scarlet.thevoidkingdom.utils.game.menu.OpenReason;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class VoidCraftingTableMenu extends BlockMenu {

    public class VoidCraftingTableMenuHolder extends BlockMenuHolder {
        @Override
        public @NotNull VoidCraftingTableMenu getMenu() {
            return VoidCraftingTableMenu.this;
        }
    }


    public VoidCraftingTableMenu(Player owner, Location blockLocation, VoidCraftingTableRecipe.RecipeCategory recipeCategory, int page) {
        super(owner, blockLocation);
        this.recipeCategory = recipeCategory;
        this.page = page;
        this.inventory = generateBaseInventory(recipeCategory);
    }

    private int page;
    private VoidCraftingTableRecipe.RecipeCategory recipeCategory;

    private static final List<Integer> SLOTS = List.of(12, 13, 14, 15, 16, 21, 22, 23, 24, 25, 30, 31, 32, 33, 34, 39, 40, 41, 42, 43);

    public void update(VoidCraftingTableRecipe.RecipeCategory recipeCategory, int page) {
        this.recipeCategory = recipeCategory;
        List<VoidCraftingTableRecipe> recipes;
        String fontPlaceHolder = "";
        if (recipeCategory == null) {
            recipes = new ArrayList<>(RegisterManager.getManager(VoidCraftingTableRecipe.class).getValidList());
            fontPlaceHolder = ":gui_void_crafting_table_1:";
        } else {
            recipes = new ArrayList<>(VoidCraftingTableRecipe.RECIPE_CATEGORY_MAP.get(recipeCategory));
            switch (recipeCategory) {
                case BLOCK -> fontPlaceHolder = ":gui_void_crafting_table_2:";
                case MATERIAL -> fontPlaceHolder = ":gui_void_crafting_table_3:";
                case TOOL_ADD_ACCESSORY -> fontPlaceHolder = ":gui_void_crafting_table_4:";
                case WEAPON -> fontPlaceHolder = ":gui_void_crafting_table_5:";
                case ARMOR -> fontPlaceHolder = ":gui_void_crafting_table_6:";
            }
        }
        Map<String, Integer> playerIngredients = new HashMap<>();
        PlayerInventory playerInventory = owner.getInventory();
        for (ItemStack itemStack : playerInventory) {
            if (itemStack == null) {
                continue;
            }
            String namespaceId = ItemsAdderUtils.getItemNamespaceId(itemStack);
            playerIngredients.put(namespaceId, playerIngredients.getOrDefault(namespaceId, 0) + itemStack.getAmount());
        }
        List<CheckedRecipe> sortedList = new ArrayList<>();
        List<CheckedRecipe> otherRecipeList = new ArrayList<>();
        for (VoidCraftingTableRecipe recipe : recipes) {
            CheckedRecipe checkedRecipe = new CheckedRecipe(playerIngredients, recipe);
            if (checkedRecipe.isIngredientMeet()) {
                sortedList.add(checkedRecipe);
            } else {
                otherRecipeList.add(checkedRecipe);
            }
        }
        sortedList.addAll(otherRecipeList);
        int maxPage = recipes.size() / 20 + 1;
        if (page > maxPage) {
            page = maxPage;
        } else if (page < 1) {
            page = 1;
        }
        this.page = page;
        int beginIndex = (page - 1) * 20;
        int endIndex = page * 20 - 1;
        StringBuilder titleBuilder = new StringBuilder(":offset_-14:" + fontPlaceHolder + ":offset_-124:");
        for (int i = beginIndex; i < Math.min(sortedList.size(), endIndex + 1); i++) {
            CheckedRecipe checkedRecipe = sortedList.get(i);
            int j = Math.floorMod(i, 20);
            if (checkedRecipe.isIngredientMeet()) {
                switch (j) {
                    case 0, 1, 2, 3 -> titleBuilder.append("<green>%img_slot_background_2%</green>:offset_1:");
                    case 4 -> titleBuilder.append("<green>%img_slot_background_2%</green>:offset_-89:");
                    case 5, 6, 7, 8 -> titleBuilder.append("<green>%img_slot_background_3%</green>:offset_1:");
                    case 9 -> titleBuilder.append("<green>%img_slot_background_3%</green>:offset_-89:");
                    case 10, 11, 12, 13 -> titleBuilder.append("<green>%img_slot_background_4%</green>:offset_1:");
                    case 14 -> titleBuilder.append("<green>%img_slot_background_4%</green>:offset_-89:");
                    case 15, 16, 17, 18 -> titleBuilder.append("<green>%img_slot_background_5%</green>:offset_1:");
                    case 19 -> titleBuilder.append("<green>%img_slot_background_5%</green>");
                }
            } else {
                switch (j) {
                    case 0, 1, 2, 3 -> titleBuilder.append("<red>%img_slot_background_2%</red>:offset_1:");
                    case 4 -> titleBuilder.append("<red>%img_slot_background_2%</red>:offset_-89:");
                    case 5, 6, 7, 8 -> titleBuilder.append("<red>%img_slot_background_3%</red>:offset_1:");
                    case 9 -> titleBuilder.append("<red>%img_slot_background_3%</red>:offset_-89:");
                    case 10, 11, 12, 13 -> titleBuilder.append("<red>%img_slot_background_4%</red>:offset_1:");
                    case 14 -> titleBuilder.append("<red>%img_slot_background_4%</red>:offset_-89:");
                    case 15, 16, 17, 18 -> titleBuilder.append("<red>%img_slot_background_5%</red>:offset_1:");
                    case 19 -> titleBuilder.append("<red>%img_slot_background_5%</red>");
                }
            }
        }
        this.setTitle(TheVoidKingdomUtils.deserializeMiniMessage(titleBuilder.toString(), true));
        Iterator<Integer> soltIterator = SLOTS.iterator();
        for (int i = beginIndex; i < Math.min(sortedList.size(), endIndex + 1); i++) {
            inventory.setItem(soltIterator.next(), sortedList.get(i).getDisplayItemStack());
        }
        while (soltIterator.hasNext()) {
            inventory.setItem(soltIterator.next(), null);
        }
        if (page > 1) {
            inventory.setItem(48, VoidCraftingTableMenuIcon.LAST.getItemStack());
        } else {
            inventory.setItem(48, null);
        }
        if (page < maxPage) {
            inventory.setItem(52, VoidCraftingTableMenuIcon.NEXT.getItemStack());
        } else {
            inventory.setItem(52, null);
        }
    }

    public void update() {
        update(recipeCategory, page);
    }

    public Inventory generateBaseInventory(VoidCraftingTableRecipe.RecipeCategory recipeCategory) {
        String fontPlaceHolder = "";
        if (recipeCategory == null) {
            fontPlaceHolder = ":gui_void_crafting_table_1:";
        } else {
            switch (recipeCategory) {
                case BLOCK -> fontPlaceHolder = ":gui_void_crafting_table_2:";
                case MATERIAL -> fontPlaceHolder = ":gui_void_crafting_table_3:";
                case TOOL_ADD_ACCESSORY -> fontPlaceHolder = ":gui_void_crafting_table_4:";
                case WEAPON -> fontPlaceHolder = ":gui_void_crafting_table_5:";
                case ARMOR -> fontPlaceHolder = ":gui_void_crafting_table_6:";
            }
        }
        Inventory baseInventory = Bukkit.createInventory(new VoidCraftingTableMenuHolder(), 54, TheVoidKingdomUtils.deserializeMiniMessage(":offset_-14:" + fontPlaceHolder, false));
        baseInventory.setItem(0, VoidCraftingTableMenuIcon.CATEGORY_ALL.getItemStack());
        baseInventory.setItem(9, VoidCraftingTableMenuIcon.CATEGORY_BLOCK.getItemStack());
        baseInventory.setItem(18, VoidCraftingTableMenuIcon.CATEGORY_MATERIAL.getItemStack());
        baseInventory.setItem(27, VoidCraftingTableMenuIcon.CATEGORY_TOOL_ADD_ACCESSORY.getItemStack());
        baseInventory.setItem(36, VoidCraftingTableMenuIcon.CATEGORY_WEAPON.getItemStack());
        baseInventory.setItem(45, VoidCraftingTableMenuIcon.CATEGORY_ARMOR.getItemStack());
        return baseInventory;
    }

    public void nextPage() {
        update(recipeCategory, page + 1);
    }

    public void lastPage() {
        update(recipeCategory, page - 1);
    }


    @Override
    public void open(OpenReason openReason) {
        if (openReason == OpenReason.OPEN_NEW) {
            owner.openInventory(inventory);
            MenuProtect.addDenyAll(owner, this);
        }
    }

    @Override
    public void close(CloseReason closeReason) {
        switch (closeReason) {
            case BLOCK_BREAK, TOO_FAR, DEFAULT -> {
                this.inventory.close();
                MenuProtect.removeDenyAll(owner);
            }
            case OPEN_OTHER -> {
                MenuProtect.removeDenyAll(owner);
            }
        }
    }

    public int getPage() {
        return page;
    }

    public VoidCraftingTableRecipe.RecipeCategory getRecipeCategory() {
        return recipeCategory;
    }
}

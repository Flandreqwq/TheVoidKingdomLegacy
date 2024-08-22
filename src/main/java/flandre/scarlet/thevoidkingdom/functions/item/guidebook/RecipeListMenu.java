package flandre.scarlet.thevoidkingdom.functions.item.guidebook;

import flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe.*;
import flandre.scarlet.thevoidkingdom.utils.framework.register.RegisterManager;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RecipeListMenu extends GuideBookMenu {
    public class RecipeListMenuHolder extends GuideBookMenuHolder implements SwitchableMenuHolder {
        public @NotNull RecipeListMenu getMenu() {
            return RecipeListMenu.this;
        }
    }

    private final List<VKRecipe> recipes;
    private final String resultNamespaceId;
    private final boolean isWatchRecipe;
    private final int page;

    public RecipeListMenu(Player owner, String resultNamespaceId, List<VKRecipe> recipes, boolean isSearchRecipe, int page) {
        super(owner);
        this.resultNamespaceId = resultNamespaceId;
        this.recipes = recipes;
        this.isWatchRecipe = isSearchRecipe;
        int maxPage = recipes.size();
        if (page > maxPage) {
            page = maxPage;
        } else if (page < 1) {
            page = 1;
        }
        this.page = page;
        if (recipes.size() > 0) {
            this.inventory = recipes.get(page - 1).generateInventory(new RecipeListMenuHolder());
            inventory.setItem(49, GuideBookMenuIcon.CLOSE.getItemStack());
            if (page > 1) {
                inventory.setItem(46, GuideBookMenuIcon.LAST.getItemStack());
            }
            if (page < maxPage) {
                inventory.setItem(52, GuideBookMenuIcon.NEXT.getItemStack());
            }
        } else {
            this.inventory = null;
        }
    }

    public RecipeListMenu(Player owner, String resultNamespaceId, boolean isSearchRecipe, int page) {
        super(owner);
        this.resultNamespaceId = resultNamespaceId;
        this.recipes = new ArrayList<>();
        if (isSearchRecipe) {
            searchAllRecipe(recipes, resultNamespaceId);
        } else {
            searchAllUsage(recipes, resultNamespaceId);
        }
        this.isWatchRecipe = isSearchRecipe;
        int maxPage = recipes.size();
        if (page > maxPage) {
            page = maxPage;
        } else if (page < 1) {
            page = 1;
        }
        this.page = page;
        if (recipes.size() > 0) {
            this.inventory = recipes.get(page - 1).generateInventory(new RecipeListMenuHolder());
            inventory.setItem(49, GuideBookMenuIcon.CLOSE.getItemStack());
            if (page > 1) {
                inventory.setItem(46, GuideBookMenuIcon.LAST.getItemStack());
            }
            if (page < maxPage) {
                inventory.setItem(52, GuideBookMenuIcon.NEXT.getItemStack());
            }
        } else {
            this.inventory = null;
        }
    }

    private static final List<Class<? extends VKRecipe>> searchRecipeTypes = List.of(
            MiningRecipe.class,
            InteractBlockRecipe.class,
            StripLogRecipe.class,
            VKShapedRecipe.class,
            VKShapelessRecipe.class,
            VKFurnaceRecipe.class,
            VKBlastingRecipe.class,
            VKSmokingRecipe.class,
            VKCampfireRecipe.class,
            VKStoneCuttingRecipe.class,
            VoidCraftingTableRecipe.class,
            MobDropsRecipe.class,
            OpenBoxRecipe.class,
            SpellcardPackRecipe.class,
            CuttingBoardRecipe.class
    );

    public void searchAllUsage(List<VKRecipe> recipes, String usedItemNamespaceId) {
        for (Class<? extends VKRecipe> clazz : searchRecipeTypes) {
            RegisterManager.getManager(clazz).getValidList().forEach(recipe -> {
                if (recipe.checkInput(usedItemNamespaceId)) {
                    recipes.add(recipe);
                }
            });
        }
    }


    public void searchAllRecipe(List<VKRecipe> recipes, String resultNamespaceId) {
        for (Class<? extends VKRecipe> clazz : searchRecipeTypes) {
            RegisterManager.getManager(clazz).getValidList().forEach(recipe -> {
                if (recipe.checkResult(resultNamespaceId)) {
                    recipes.add(recipe);
                }
            });
        }
    }


    public int getPage() {
        return page;
    }

    public List<VKRecipe> getRecipes() {
        return recipes;
    }

    public String getResultNamespaceId() {
        return resultNamespaceId;
    }

    public boolean isWatchRecipe() {
        return isWatchRecipe;
    }
}

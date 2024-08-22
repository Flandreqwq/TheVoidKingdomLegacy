package flandre.scarlet.thevoidkingdom.functions.item.guidebook;

import com.molean.isletopia.framework.annotations.Bean;
import dev.lone.itemsadder.api.CustomStack;
import dev.lone.itemsadder.api.ItemsAdder;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe.MiningRecipe;
import flandre.scarlet.thevoidkingdom.utils.game.menu.OpenReason;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@Bean
public class RecipeMenuListener implements Listener {
    private static final List<ClickType> clickTypes = List.of(
            ClickType.LEFT,
            ClickType.RIGHT,
            ClickType.SHIFT_LEFT,
            ClickType.SHIFT_RIGHT
    );

    @EventHandler(priority = EventPriority.NORMAL)
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        InventoryHolder inventoryHolder = inventory.getHolder();
        if (inventoryHolder instanceof RecipeListMenu.RecipeListMenuHolder recipeListMenuHolder) {
            int clickSlot = event.getRawSlot();
            if (clickSlot < 0 || clickSlot > inventory.getSize()) {
                return;
            }
            ItemStack clickIcon = inventory.getItem(clickSlot);
            if (clickIcon == null) {
                return;
            }
            ClickType click = event.getClick();
            if (!clickTypes.contains(click)) {
                return;
            }
            RecipeListMenu recipeListMenu = recipeListMenuHolder.getMenu();
            if (clickIcon.equals(GuideBookMenuIcon.CLOSE.getItemStack())) {
                recipeListMenu.backToLastMenu();
            } else if (clickIcon.equals(GuideBookMenuIcon.NEXT.getItemStack())) {
                new RecipeListMenu(player, recipeListMenu.getResultNamespaceId(), recipeListMenu.getRecipes(),
                        recipeListMenu.isWatchRecipe(), recipeListMenu.getPage() + 1).open(OpenReason.NEXT_PAGE);
            } else if (clickIcon.equals(GuideBookMenuIcon.LAST.getItemStack())) {
                new RecipeListMenu(player, recipeListMenu.getResultNamespaceId(), recipeListMenu.getRecipes(),
                        recipeListMenu.isWatchRecipe(), recipeListMenu.getPage() - 1).open(OpenReason.LAST_PAGE);
            } else {
                String nowNamespaceId = recipeListMenu.getResultNamespaceId();
                boolean nowIsWatchRecipe = recipeListMenu.isWatchRecipe();
                CustomStack customStack = CustomStack.byItemStack(clickIcon);
                boolean wantToWatchRecipe = !click.isShiftClick();
                if (customStack != null) {
                    //点击的物品是ia物品
                    if (nowIsWatchRecipe == wantToWatchRecipe && customStack.getNamespacedID().equals(nowNamespaceId)) {
                        return;
                    }
                    //点击采集配方里面的查看工具
                    if (customStack.getNamespace().equals("vkguis")) {
                        String[] strings = customStack.getId().split("_");
                        String toolType = strings[1];
                        int toolLevel = Integer.parseInt(strings[2]);
                        if (toolType.equals("hand")) {
                            return;
                        }
                        List<String> namespaceIds = new ArrayList<>();
                        if (toolLevel <= 1) {
                            namespaceIds.add("WOODEN_" + toolType.toUpperCase());
                        }
                        if (toolLevel <= 2) {
                            namespaceIds.add("STONE_" + toolType.toUpperCase());
                        }
                        List<CustomStack> customStackList = ItemsAdder.getAllItems("vktools");
                        for (CustomStack customStack1 : customStackList) {
                            String namespaceId = customStack1.getNamespacedID();
                            String id = customStack1.getId();
                            if (!id.contains(toolType)) {
                                continue;
                            }
                            int toolLevel1 = MiningRecipe.getToolLevel(namespaceId);
                            if (toolLevel1 < toolLevel) {
                                continue;
                            }
                            namespaceIds.add(namespaceId);
                        }
                        Category category = new Category("_tool", "§6❊ §l合适的工具§r", null, "", namespaceIds);
                        new CategoryMenu(player, category, 1).open(OpenReason.OPEN_NEW);
                        return;
                    }
                    //打开对应物品的配方菜单
                    new RecipeListMenu(player, customStack.getNamespacedID(), wantToWatchRecipe, 1).open(OpenReason.OPEN_NEW);
                } else {
                    //点击的物品是原版物品
                    if (clickIcon.getType().equals(Material.SHEARS) && clickIcon.getItemMeta().hasDisplayName()) {
                        return;
                    }
                    if (nowIsWatchRecipe == wantToWatchRecipe && clickIcon.getType().toString().equals(nowNamespaceId)) {
                        return;
                    }
                    new RecipeListMenu(player, clickIcon.getType().toString(), wantToWatchRecipe, 1).open(OpenReason.OPEN_NEW);
                }
            }
        }
    }
}

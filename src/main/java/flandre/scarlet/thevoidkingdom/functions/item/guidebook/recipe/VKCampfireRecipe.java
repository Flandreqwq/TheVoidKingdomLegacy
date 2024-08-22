package flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe;

import flandre.scarlet.thevoidkingdom.functions.item.guidebook.GuideBookMenuIcon;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.RecipeListMenu;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.SwitchedItem;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.SwitchedItemManager;
import flandre.scarlet.thevoidkingdom.utils.framework.register.Register;
import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.CampfireRecipe;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@Register(registerName = "营火配方")
public record VKCampfireRecipe(CampfireRecipe campfireRecipe) implements VKRecipe {

    @Override
    public String getMenuImagePlaceholder() {
        return ":gui_guide_campfire:";
    }

    @Override
    public String getMenuDisplayName() {
        return "营火";
    }

    @Override
    public Inventory generateInventory(RecipeListMenu.RecipeListMenuHolder holder) {
        Inventory inventory = Bukkit.createInventory(holder, 54, Component.text(VKRecipe.generateTitle(this)));
        RecipeChoice recipeChoice = campfireRecipe.getInputChoice();
        SwitchedItemManager.create(inventory,new SwitchedItem(VKRecipe.getBukkitRecipeChoiceItemStacks(recipeChoice), SwitchedItem.SwitchMode.NORMAL,11));
        ItemStack fireIcon = GuideBookMenuIcon.COOK_INFO.getItemStack().clone();
        ItemMeta itemMeta1 = fireIcon.getItemMeta();
        itemMeta1.lore(TheVoidKingdomUtils.deserializeList(List.of(
                "<!italic><white>%img_short_line_3%",
                "<!italic><gold> %img_info% 烧制时长: <white>" + String.format("%.1f", campfireRecipe.getCookingTime() / 20.0) + "<gold> 秒",
                "<!italic><gold> %img_info% 烧制经验: <white>" + String.format("%.2f", campfireRecipe.getExperience()) + "<gold> Exp",
                "<!italic><white>%img_short_line_3%"
        ), true));
        fireIcon.setItemMeta(itemMeta1);
        inventory.setItem(20, fireIcon);
        ItemStack resultItemStack = campfireRecipe.getResult();
        inventory.setItem(24, resultItemStack);
        return inventory;
    }

    @Override
    public boolean checkInput(String namespaceId) {
        return VKRecipe.checkChoice(campfireRecipe.getInputChoice(), namespaceId);
    }

    @Override
    public boolean checkResult(String namespaceId) {
        return VKRecipe.checkItemStack(campfireRecipe.getResult(), namespaceId);
    }


    @Override
    public boolean isValid() {
        return true;
    }
}

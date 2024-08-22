package flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe;

import flandre.scarlet.thevoidkingdom.functions.item.guidebook.GuideBookMenuIcon;
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
import org.bukkit.inventory.SmokingRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@Register(registerName = "烟熏炉配方")
public record VKSmokingRecipe(SmokingRecipe smokingRecipe) implements VKRecipe {

    @Override
    public String getMenuImagePlaceholder() {
        return ":gui_guide_smoker:";
    }

    @Override
    public String getMenuDisplayName() {
        return "烟熏炉";
    }

    @Override
    public Inventory generateInventory(RecipeListMenu.RecipeListMenuHolder holder) {
        Inventory inventory = Bukkit.createInventory(holder, 54, Component.text(VKRecipe.generateTitle(this)));
        RecipeChoice recipeChoice = smokingRecipe.getInputChoice();
        SwitchedItemManager.create(inventory,new SwitchedItem(VKRecipe.getBukkitRecipeChoiceItemStacks(recipeChoice), SwitchedItem.SwitchMode.NORMAL,11));
        ItemStack fireIcon = GuideBookMenuIcon.COOK_INFO.getItemStack().clone();
        ItemMeta itemMeta1 = fireIcon.getItemMeta();
        itemMeta1.lore(TheVoidKingdomUtils.deserializeList(List.of(
                "<!italic><white>%img_short_line_3%",
                "<!italic><gold> %img_info% 烧制时长: <white>" + String.format("%.1f", smokingRecipe.getCookingTime() / 20.0) + "<gold> 秒",
                "<!italic><gold> %img_info% 烧制经验: <white>" + String.format("%.2f", smokingRecipe.getExperience()) + "<gold> Exp",
                "<!italic><white>%img_short_line_3%"
        ), true));
        fireIcon.setItemMeta(itemMeta1);
        inventory.setItem(20, fireIcon);
        inventory.setItem(29, GuideBookMenuIcon.getCoalFuelIcon());
        ItemStack resultItemStack = smokingRecipe.getResult();
        inventory.setItem(24, resultItemStack);
        return inventory;
    }

    @Override
    public boolean checkInput(String namespaceId) {
        return VKRecipe.checkChoice(smokingRecipe.getInputChoice(), namespaceId);
    }

    @Override
    public boolean checkResult(String namespaceId) {
        return VKRecipe.checkItemStack(smokingRecipe.getResult(), namespaceId);
    }


    @Override
    public boolean isValid() {
        return true;
    }
}

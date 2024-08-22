package flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe.creator;

import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe.voidcarftingtable.StationRecipe;
import flandre.scarlet.thevoidkingdom.utils.framework.RegisterManager;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.crafting.CraftingStation;
import net.Indyuce.mmoitems.api.crafting.recipe.CraftingRecipe;
import net.Indyuce.mmoitems.api.crafting.recipe.Recipe;
import net.Indyuce.mmoitems.manager.CraftingManager;

import java.util.Collection;

@Bean
public class StationRecipeCreator implements RecipeCreator {
    private static final RegisterManager<StationRecipe> registerManager = RegisterManager.getManager(StationRecipe.class);


    @Override
    public void createAll() {
        registerManager.getRawList().clear();
        CraftingManager craftingManager = MMOItems.plugin.getCrafting();
        Collection<CraftingStation> craftingStations = craftingManager.getAll();
        for (CraftingStation craftingStation : craftingStations) {
            if (craftingStation.getId().contains("void-crafting-table-")) {
                continue;
            }
            for (Recipe recipe : craftingStation.getRecipes()) {
                if (recipe instanceof CraftingRecipe craftingRecipe) {
                    registerManager.create(new StationRecipe(craftingStation, craftingRecipe));
                }
            }
        }
    }
}

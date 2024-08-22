package flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe.creator;

import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe.*;
import flandre.scarlet.thevoidkingdom.utils.framework.register.RegisterManager;
import flandre.scarlet.thevoidkingdom.utils.framework.register.Registrable;
import org.bukkit.Bukkit;
import org.bukkit.inventory.*;

import java.util.Iterator;
import java.util.Set;

@Bean
public class VanillaRecipeCreator implements RecipeCreator {
    private static final Set<Class<? extends Registrable>> vanillaRecipeTypes = Set.of(
            VKShapedRecipe.class,
            VKShapelessRecipe.class,
            VKFurnaceRecipe.class,
            VKBlastingRecipe.class,
            VKSmokingRecipe.class,
            VKCampfireRecipe.class,
            VKStoneCuttingRecipe.class
    );

    private static final RegisterManager<VKShapedRecipe> vkShapedRecipeRegisterManager = RegisterManager.getManager(VKShapedRecipe.class);
    private static final RegisterManager<VKShapelessRecipe> vkShapelessRecipeRegisterManager = RegisterManager.getManager(VKShapelessRecipe.class);
    private static final RegisterManager<VKFurnaceRecipe> vkFurnaceRecipeRegisterManager = RegisterManager.getManager(VKFurnaceRecipe.class);
    private static final RegisterManager<VKBlastingRecipe> vkBlastingRecipeRegisterManager = RegisterManager.getManager(VKBlastingRecipe.class);
    private static final RegisterManager<VKSmokingRecipe> vkSmokingRecipeRegisterManager = RegisterManager.getManager(VKSmokingRecipe.class);
    private static final RegisterManager<VKCampfireRecipe> vkCampfireRecipeRegisterManager = RegisterManager.getManager(VKCampfireRecipe.class);
    private static final RegisterManager<VKStoneCuttingRecipe> vkStoneCuttingRecipeRegisterManager = RegisterManager.getManager(VKStoneCuttingRecipe.class);


    @Override
    public void initialize() {
        Iterator<Recipe> recipeIterator = Bukkit.recipeIterator();
        while (recipeIterator.hasNext()) {
            Recipe recipe = recipeIterator.next();
            if (recipe instanceof ShapedRecipe shapedRecipe) {
                if (!shapedRecipe.getKey().namespace().contains("zzzfake")) {
                    vkShapedRecipeRegisterManager.create(new VKShapedRecipe(shapedRecipe));
                }
            } else if (recipe instanceof ShapelessRecipe shapelessRecipe) {
                if (!shapelessRecipe.getKey().namespace().contains("zzzfake")) {
                    vkShapelessRecipeRegisterManager.create(new VKShapelessRecipe(shapelessRecipe));
                }
            } else if (recipe instanceof FurnaceRecipe furnaceRecipe) {
                vkFurnaceRecipeRegisterManager.create(new VKFurnaceRecipe(furnaceRecipe));
            } else if (recipe instanceof BlastingRecipe blastingRecipe) {
                vkBlastingRecipeRegisterManager.create(new VKBlastingRecipe(blastingRecipe));
            } else if (recipe instanceof SmokingRecipe smokingRecipe) {
                vkSmokingRecipeRegisterManager.create(new VKSmokingRecipe(smokingRecipe));
            } else if (recipe instanceof CampfireRecipe campfireRecipe) {
                vkCampfireRecipeRegisterManager.create(new VKCampfireRecipe(campfireRecipe));
            } else if (recipe instanceof StonecuttingRecipe stonecuttingRecipe) {
                vkStoneCuttingRecipeRegisterManager.create(new VKStoneCuttingRecipe(stonecuttingRecipe));
            }
        }
    }

    @Override
    public void clear() {
        for (Class<? extends Registrable> clazz : vanillaRecipeTypes) {
            RegisterManager.getManager(clazz).getRawList().clear();
        }
    }
}

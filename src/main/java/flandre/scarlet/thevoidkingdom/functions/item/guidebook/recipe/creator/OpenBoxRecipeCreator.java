package flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe.creator;

import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe.OpenBoxRecipe;
import flandre.scarlet.thevoidkingdom.utils.framework.register.RegisterManager;
import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.drops.Drop;
import io.lumine.mythic.core.drops.DropTable;

import java.util.*;

@Bean
public class OpenBoxRecipeCreator implements RecipeCreator {
    private static final RegisterManager<OpenBoxRecipe> registerManager = RegisterManager.getManager(OpenBoxRecipe.class);

    private static final Map<String, String> map = new HashMap<>();

    static {
        map.put("木匣", "vkblocks:wooden_box");
        map.put("青铜匣", "vkblocks:bronze_box");
        map.put("金匣", "vkblocks:golden_box");
        map.put("虚界宝匣", "vkblocks:void_box");
    }

    @Override
    public void initialize() {
        for (String boxMobName : map.keySet()) {
            MythicMob mythicMob = MythicBukkit.inst().getMobManager().getMythicMob(boxMobName).orElse(null);
            if (mythicMob == null) {
                TheVoidKingdom.LOGGER.warning("不存在的MythicMobs怪物: " + boxMobName);
                continue;
            }
            DropTable dropTable = mythicMob.getDropTable();
            List<Drop> dropList = MobDropsRecipeCreator.getSimpleDrops(dropTable);
            Set<String> dropItemNamespaceIds = new HashSet<>();
            for (Drop drop : dropList) {
                String namespaceId = MobDropsRecipeCreator.getDropNamespaceId(drop);
                if (namespaceId != null) {
                    dropItemNamespaceIds.add(namespaceId);
                }
            }
            registerManager.create(new OpenBoxRecipe(map.get(boxMobName), dropItemNamespaceIds));
        }
    }

    @Override
    public void clear() {
        registerManager.getRawList().clear();
    }
}

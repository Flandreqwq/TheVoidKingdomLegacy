package flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe.creator;

import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.functions.item.guidebook.recipe.SpellcardPackRecipe;
import flandre.scarlet.thevoidkingdom.utils.framework.register.RegisterManager;
import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.drops.Drop;
import io.lumine.mythic.core.drops.DropTable;

import java.util.*;

@Bean
public class SpellcardPackRecipeCreator implements RecipeCreator {
    private static final RegisterManager<SpellcardPackRecipe> registerManager = RegisterManager.getManager(SpellcardPackRecipe.class);
    private static final Map<String, String> map = new HashMap<>();

    static {
        map.put("符卡包-普通", "vkmaterials:common_spellcard_pack");
    }

    @Override
    public void initialize() {
        for (String cardBagMobName : map.keySet()) {
            MythicMob mythicMob = MythicBukkit.inst().getMobManager().getMythicMob(cardBagMobName).orElse(null);
            if (mythicMob == null) {
                TheVoidKingdom.LOGGER.warning("不存在的MythicMobs怪物: " + cardBagMobName);
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
            registerManager.create(new SpellcardPackRecipe(map.get(cardBagMobName), dropItemNamespaceIds));
        }
    }

    @Override
    public void clear() {
        registerManager.getRawList().clear();
    }
}

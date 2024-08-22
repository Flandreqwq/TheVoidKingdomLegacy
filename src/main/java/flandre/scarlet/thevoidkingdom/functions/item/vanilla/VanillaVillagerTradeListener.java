package flandre.scarlet.thevoidkingdom.functions.item.vanilla;

import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.utils.game.TheVoidKingdomUtils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.List;

@Bean
public class VanillaVillagerTradeListener implements Listener {
    @EventHandler(priority = EventPriority.NORMAL)
    public void on(VillagerAcquireTradeEvent event) {
        MerchantRecipe merchantRecipe = event.getRecipe();
        ItemStack result = merchantRecipe.getResult();
        Material material = result.getType();
        switch (material) {
            case ENCHANTED_BOOK, GOLDEN_CARROT, EXPERIENCE_BOTTLE -> {
                event.setCancelled(true);
                return;
            }
            case LEATHER_HELMET, LEATHER_CHESTPLATE, LEATHER_LEGGINGS, LEATHER_BOOTS, LEATHER_HORSE_ARMOR -> {
                LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) result.getItemMeta();
                leatherArmorMeta.setColor(null);
                result.setItemMeta(leatherArmorMeta);
            }
            case BOW, CROSSBOW, FISHING_ROD -> {
                if (!result.getItemMeta().getEnchants().isEmpty()) {
                    event.setCancelled(true);
                    return;
                }
            }
        }
        List<ItemStack> list = merchantRecipe.getIngredients();
        list.forEach(itemStack -> {
            if (itemStack.getType().equals(Material.RABBIT_FOOT)) {
                event.setCancelled(true);
            }
        });
        TheVoidKingdomUtils.checkClearVanillaStat(result);
        MerchantRecipe newTrade = new MerchantRecipe(result,
                merchantRecipe.getUses(),
                merchantRecipe.getMaxUses(),
                merchantRecipe.hasExperienceReward(),
                merchantRecipe.getVillagerExperience(),
                merchantRecipe.getPriceMultiplier(),
                merchantRecipe.getDemand(),
                merchantRecipe.getSpecialPrice(),
                merchantRecipe.shouldIgnoreDiscounts()
        );
        List<ItemStack> ingredients = merchantRecipe.getIngredients();
        newTrade.setIngredients(ingredients);
        event.setRecipe(newTrade);
    }
}

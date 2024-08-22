package flandre.scarlet.thevoidkingdom.functions.item.vanilla;

import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.utils.TheVoidKingdomUtils;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Bean
public class VanillaMonsterArmorListener implements Listener {
    private static final List<EntityType> list = List.of(
            EntityType.ZOMBIE,
            EntityType.ZOMBIE_VILLAGER,
            EntityType.HUSK,
            EntityType.DROWNED,
            EntityType.SKELETON,
            EntityType.STRAY,
            EntityType.PIGLIN,
            EntityType.PILLAGER,
            EntityType.VEX,
            EntityType.VINDICATOR,
            EntityType.ZOMBIFIED_PIGLIN
    );

    @EventHandler(priority = EventPriority.HIGH)
    public void on(CreatureSpawnEvent event) {
        if (event.isCancelled()) {
            return;
        }
        LivingEntity livingEntity = event.getEntity();
        if (!list.contains(event.getEntityType())) {
            return;
        }
        EntityEquipment entityEquipment = livingEntity.getEquipment();
        if (entityEquipment == null) {
            return;
        }
        entityEquipment.setHelmet(clear(entityEquipment.getHelmet()), true);
        entityEquipment.setChestplate(clear(entityEquipment.getChestplate()), true);
        entityEquipment.setLeggings(clear(entityEquipment.getLeggings()), true);
        entityEquipment.setBoots(clear(entityEquipment.getBoots()), true);
        entityEquipment.setItemInMainHand(clear(entityEquipment.getItemInMainHand()), true);
        entityEquipment.setItemInOffHand(clear(entityEquipment.getItemInOffHand()), true);
    }

    private ItemStack clear(ItemStack itemStack) {
        Material material = itemStack.getType();
        if (TheVoidKingdomUtils.VANILLA_ARMORS.contains(material) ||
                TheVoidKingdomUtils.VANILLA_TOOL.contains(material) ||
                TheVoidKingdomUtils.VANILLA_WEAPON.contains(material)) {
            return TheVoidKingdomUtils.clearVanillaStat(itemStack);
        }
        return itemStack;
    }
}

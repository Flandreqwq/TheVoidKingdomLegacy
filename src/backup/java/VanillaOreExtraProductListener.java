package flandre.scarlet.thevoidkingdom.functions.block.vanilla;

import com.molean.isletopia.framework.annotations.Bean;
import de.tr7zw.nbtapi.NBTItem;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.inventory.ItemStack;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

@Bean
public class VanillaOreExtraProductListener implements Listener {

    public void extraProduct(Location location, String miType, String miId, double possible, ProductType productType) {
        Random random = new Random();
        if (random.nextDouble() < possible) {
            MMOItem mmoitem = MMOItems.plugin.getMMOItem(MMOItems.plugin.getTypes().get(miType), miId);
            if (mmoitem == null) {
                TheVoidKingdom.LOGGER.warning("不存在的mi物品: " + miType + " " + miId);
                return;
            }
            ItemStack dropItemStack = mmoitem.newBuilder().build();
            assert dropItemStack != null;
            if (productType.equals(ProductType.CRYSTAL)) {
                NBTItem nbtItem = new NBTItem(dropItemStack);
                double size = nbtItem.getDouble("MMOITEMS_CUSTOM_SIZE");
                double polish = nbtItem.getDouble("MMOITEMS_CUSTOM_POLISH");
                double purity = nbtItem.getDouble("MMOITEMS_CUSTOM_PURITY");
                nbtItem.setDouble("MMOITEMS_CUSTOM_SIZE", new BigDecimal(size).setScale(2, RoundingMode.HALF_UP).doubleValue());
                nbtItem.setDouble("MMOITEMS_CUSTOM_POLISH", new BigDecimal(polish).setScale(2, RoundingMode.HALF_UP).doubleValue());
                nbtItem.setDouble("MMOITEMS_CUSTOM_PURITY", new BigDecimal(purity).setScale(2, RoundingMode.HALF_UP).doubleValue());
                dropItemStack = nbtItem.getItem();
            }
            location.getWorld().dropItem(location.clone().add(0.5, 0, 0.5), dropItemStack);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void on(BlockDropItemEvent event) {
        if (event.isCancelled()) {
            return;
        }
        Player player = event.getPlayer();
        if (!player.getGameMode().equals(GameMode.SURVIVAL)) {
            return;
        }
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if (itemStack.getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
            return;
        }
        BlockState blockState = event.getBlockState();
        Location location = blockState.getLocation();
        switch (blockState.getType()) {
            case LAPIS_ORE, DEEPSLATE_LAPIS_ORE ->
                    extraProduct(location, "MATERIAL_ENCHANT", "YUNMOQINGJINSHICU", 0.25, ProductType.CRYSTAL);
            case REDSTONE_ORE, DEEPSLATE_REDSTONE_ORE ->
                    extraProduct(location, "MATERIAL_ENCHANT", "ZHUSHA", 0.15, ProductType.CRYSTAL);
        }
    }

    private enum ProductType {
        CRYSTAL,
        NORMAL
    }
}

package flandre.scarlet.thevoidkingdom.functions.block.direction;

import com.molean.isletopia.framework.annotations.Bean;
import dev.lone.itemsadder.api.CustomBlock;
import dev.lone.itemsadder.api.CustomStack;
import dev.lone.itemsadder.api.Events.CustomBlockPlaceEvent;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.inventory.ItemStack;

@Bean
public class DirectionalBlockListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void on(CustomBlockPlaceEvent event) {
        if (event.isCancelled() || !event.isCanBuild()) {
            return;
        }
        String namespacedID = event.getNamespacedID();
        DirectionalBlock directionalBlock = DirectionalBlock.DIRECTIONAL_BLOCK_MAP.getDirectionalBlock(namespacedID);
        if (directionalBlock == null) {
            return;
        }
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Block againstBlock = event.getPlacedAgainst();
        if (againstBlock.isReplaceable()) {
            return;
        }
        Bukkit.getScheduler().runTask(TheVoidKingdom.PLUGIN, () -> {
            CustomBlock.remove(block.getLocation());
            directionalBlock.place(player.getLocation().getPitch(), player.getLocation().getYaw(), againstBlock.getFace(block), block.getLocation());
        });
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void on(ItemSpawnEvent event) {
        if (event.isCancelled()) {
            return;
        }
        Item item = event.getEntity();
        ItemStack itemStack = item.getItemStack();
        CustomStack customStack = CustomStack.byItemStack(itemStack);
        if (customStack == null) {
            return;
        }
        if (!customStack.isBlock()) {
            return;
        }
        String namespaceId = customStack.getNamespacedID();
        int amount = itemStack.getAmount();
        String origin = DirectionalBlock.DIRECTIONAL_BLOCK_MAP.getOrigin(namespaceId);
        if (origin == null) {
            return;
        }
        ItemStack resultItemStack = ItemsAdderUtils.getItemStackNoCheck(origin);
        resultItemStack.setAmount(amount);
        item.setItemStack(resultItemStack);
    }
}

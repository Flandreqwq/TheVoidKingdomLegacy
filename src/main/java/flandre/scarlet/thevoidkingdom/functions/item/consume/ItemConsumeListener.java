package flandre.scarlet.thevoidkingdom.functions.item.consume;

import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.utils.game.ItemsAdderUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

@Bean
public class ItemConsumeListener implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void on(PlayerItemConsumeEvent event) {
        if (event.isCancelled()) {
            return;
        }
        ItemStack itemStack = event.getItem();
        String namespaceId = ItemsAdderUtils.getItemNamespaceId(itemStack);
        Consumable consumable = Consumable.CONSUMABLE_KEY_MAP.get(namespaceId);
        if (consumable == null) {
            return;
        }
        for (ResultAction resultAction : consumable.resultActions()) {
            resultAction.execute(event.getPlayer());
        }
    }
}

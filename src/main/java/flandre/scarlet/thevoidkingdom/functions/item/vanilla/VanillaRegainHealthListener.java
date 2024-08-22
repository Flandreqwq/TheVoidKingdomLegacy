package flandre.scarlet.thevoidkingdom.functions.item.vanilla;

import com.molean.isletopia.framework.annotations.Bean;
import flandre.scarlet.thevoidkingdom.utils.game.CoolDownMap;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExhaustionEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import java.util.UUID;

@Bean
public class VanillaRegainHealthListener implements Listener {
    private static final CoolDownMap<UUID> cooldownMap = new CoolDownMap<>(6000);

    @EventHandler(priority = EventPriority.HIGH)
    public void on(EntityRegainHealthEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (!event.getRegainReason().equals(EntityRegainHealthEvent.RegainReason.SATIATED)) {
            return;
        }
        Entity entity = event.getEntity();
        if (!entity.getType().equals(EntityType.PLAYER)) {
            return;
        }
        Player player = (Player) entity;
        if (player.getFoodLevel() >= 20 && player.getSaturation() > 0) {
            return;
        }
        event.setCancelled(true);
//        TheVoidKingdomUtils.sendMessageToPlayer(player, "<red>已阻止无饱和度时使用饱食度回血", true);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void on(EntityExhaustionEvent event) {
        if (event.isCancelled()) {
            return;
        }
        Player player = (Player) event.getEntity();
        UUID uuid = player.getUniqueId();
        float saturation = player.getSaturation();
        float foodLevel = player.getFoodLevel();
        if (cooldownMap.isCoolDown(uuid)) {
            if (saturation == 0) {
                event.setCancelled(true);
            }
        } else if (foodLevel >= 20 && player.getExhaustion() + event.getExhaustion() >= 4 && saturation <= 1 && saturation > 0) {
            //饱和度归0的时候
            cooldownMap.add(uuid);
            event.setCancelled(true);
            player.setExhaustion(0);
            player.setSaturation(0);
//            TheVoidKingdomUtils.sendMessageToPlayer(player, "<red>饱食度不再消耗，持续8s", true);
        } else if (event.getExhaustionReason().equals(EntityExhaustionEvent.ExhaustionReason.REGEN) && saturation == 0) {
            event.setCancelled(true);
//            TheVoidKingdomUtils.sendMessageToPlayer(player, "<red>已阻止饱食度没回血而消耗", true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public  void on(PlayerItemConsumeEvent event){

    }
}

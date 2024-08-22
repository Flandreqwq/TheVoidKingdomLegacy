package flandre.scarlet.thevoidkingdom.functions.item.vanilla;

import com.molean.isletopia.framework.annotations.Bean;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@Bean
public class VanillaAbsorptionListener implements Listener {
    @EventHandler(priority = EventPriority.LOW)
    public void on(EntityPotionEffectEvent event) {
        if (!event.getEntityType().equals(EntityType.PLAYER)) {
            return;
        }
        switch (event.getAction()) {
            case ADDED, CHANGED -> {
                PotionEffect newEffect = event.getNewEffect();
                if (newEffect == null || !newEffect.getType().equals(PotionEffectType.ABSORPTION)) {
                    return;
                }
                int amplifier = newEffect.getAmplifier();
                if (amplifier > 4) {
                    event.setCancelled(true);
                } else if (amplifier >= 0) {
                    Player player = (Player) event.getEntity();
                    player.setHealthScaled(true);
                    player.setHealthScale(20.0);
                }
            }
            case CLEARED, REMOVED -> {
                PotionEffect oldEffect = event.getOldEffect();
                if (oldEffect == null || !oldEffect.getType().equals(PotionEffectType.ABSORPTION)) {
                    return;
                }
                Player player = (Player) event.getEntity();
                player.setHealthScaled(true);
                player.setHealthScale(40.0);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void on(EntityDamageEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (!event.getEntityType().equals(EntityType.PLAYER)) {
            return;
        }
        Player player = (Player) event.getEntity();
        if ((event.getDamage(EntityDamageEvent.DamageModifier.ABSORPTION) + player.getAbsorptionAmount()) <= 0) {
            player.setHealthScaled(true);
            player.setHealthScale(40.0);
        }
    }
}

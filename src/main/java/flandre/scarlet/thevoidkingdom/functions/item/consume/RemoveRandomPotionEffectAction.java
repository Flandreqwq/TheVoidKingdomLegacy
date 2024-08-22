package flandre.scarlet.thevoidkingdom.functions.item.consume;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RemoveRandomPotionEffectAction implements ResultAction{
    @Override
    public void execute(Player player) {
        List<PotionEffect> activePotionEffects = new ArrayList<>(player.getActivePotionEffects());
        if (activePotionEffects.size() == 0) {
            return;
        }
        Random random = new Random();
        player.removePotionEffect(activePotionEffects.get(random.nextInt(activePotionEffects.size())).getType());
    }

    @Override
    public boolean isValid() {
        return true;
    }
}

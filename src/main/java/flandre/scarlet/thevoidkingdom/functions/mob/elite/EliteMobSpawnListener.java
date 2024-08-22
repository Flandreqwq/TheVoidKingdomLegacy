package flandre.scarlet.thevoidkingdom.functions.mob.elite;

import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.bukkit.MythicBukkit;
import org.bukkit.Location;
import org.bukkit.event.Listener;

import java.util.Random;

public class EliteMobSpawnListener implements Listener {
    public void spawnMob(Location location, String mobName) {
        MythicMob mob = MythicBukkit.inst().getMobManager().getMythicMob(mobName).orElse(null);
        if (mob != null) {
            mob.spawn(BukkitAdapter.adapt(location), 1);
        } else {
            TheVoidKingdom.LOGGER.warning("没有找到名为 " + mobName + " 的怪物");
        }
    }

    public void spawnLevel(Location location, String mobName, double weight1, double weight2, double weight3) {
        Random random = new Random();
        double total = weight1 + weight2 + weight3;
        double var1 = random.nextDouble();
        if (var1 < weight1 / total) {
            spawnMob(location, mobName + "-1");
        } else if (var1 < (weight1 + weight2) / total) {
            spawnMob(location, mobName + "-2");
        } else {
            spawnMob(location, mobName + "-3");
        }
    }
}

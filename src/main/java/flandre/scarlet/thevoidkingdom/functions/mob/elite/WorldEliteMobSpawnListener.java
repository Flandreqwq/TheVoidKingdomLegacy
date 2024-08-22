package flandre.scarlet.thevoidkingdom.functions.mob.elite;

import com.molean.isletopia.framework.annotations.Bean;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Bean
public class WorldEliteMobSpawnListener extends EliteMobSpawnListener {
    private static final Map<Long, Long> chunkLastSpawnTimeMap = new HashMap<>();
    private static final Map<Long, Integer> chunkCountMap = new HashMap<>();

    @EventHandler(priority = EventPriority.LOW)
    public void on(CreatureSpawnEvent event) {
        CreatureSpawnEvent.SpawnReason spawnReason = event.getSpawnReason();
        if (!spawnReason.equals(CreatureSpawnEvent.SpawnReason.NATURAL) && !spawnReason.equals(CreatureSpawnEvent.SpawnReason.SPAWNER_EGG)) {
            return;
        }
        Location location = event.getLocation();
        if (!location.getWorld().getName().equals("world")) {
            return;
        }
        Random random = new Random();
        if (random.nextDouble() > 0.03) {
            return;
        }
        Long chunkKey = location.getChunk().getChunkKey();
        if (System.currentTimeMillis() - chunkLastSpawnTimeMap.getOrDefault(chunkKey, 0L) < 1000 * 60 * 15) {
            return;
        }
        Integer count = chunkCountMap.getOrDefault(chunkKey, 0);
        if (count > 10 && System.currentTimeMillis() - chunkLastSpawnTimeMap.getOrDefault(chunkKey, 0L) < 1000 * 60 * 60 * 24) {
            return;
        }
        chunkCountMap.put(chunkKey, count + 1);
        chunkLastSpawnTimeMap.put(chunkKey, System.currentTimeMillis());
        switch (event.getEntityType()) {
            case ZOMBIE -> {
                event.setCancelled(true);
                spawnLevel(location, "武装僵尸", 50, 35, 15);
            }
            case ZOMBIE_VILLAGER -> {
                event.setCancelled(true);
                spawnLevel(location, "武装僵尸村民", 50, 35, 15);
            }
            case DROWNED -> {
                event.setCancelled(true);
                spawnLevel(location, "潮涌溺尸", 50, 35, 15);
            }
            case HUSK -> {
                event.setCancelled(true);
                spawnLevel(location, "流沙尸壳", 50, 35, 15);
            }
            case SKELETON -> {
                event.setCancelled(true);
                spawnLevel(location, "敏捷骷髅", 50, 35, 15);
            }
            case STRAY -> {
                event.setCancelled(true);
                spawnLevel(location, "敏捷流浪者", 50, 35, 15);
            }
            case SPIDER -> {
                event.setCancelled(true);
                spawnLevel(location, "织网毒蛛", 50, 35, 15);
            }
            case CREEPER -> {
                event.setCancelled(true);
                spawnLevel(location, "派对苦力怕", 50, 35, 15);
            }
        }
    }
}

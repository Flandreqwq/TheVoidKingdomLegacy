package flandre.scarlet.thevoidkingdom.functions.mob.boss;

import org.bukkit.Location;
import org.bukkit.block.Biome;

import java.util.List;

public class BiomeCondition implements TotemCondition {
    private final List<Biome> requireBiomes;

    public BiomeCondition(Biome... requireBiomes) {
        this.requireBiomes = List.of(requireBiomes);
    }

    @Override
    public boolean isMeet(Location location) {
        return requireBiomes.isEmpty() || requireBiomes.contains(location.getBlock().getBiome());
    }
}

package flandre.scarlet.thevoidkingdom.functions.mob.boss;

import org.bukkit.Location;

import java.util.List;

public class WorldCondition implements TotemCondition {
    private final List<String> requireWorlds;

    public WorldCondition(String... requireWorlds) {
        this.requireWorlds = List.of(requireWorlds);
    }

    @Override
    public boolean isMeet(Location location) {
        return requireWorlds.isEmpty() || requireWorlds.contains(location.getWorld().getName());
    }
}

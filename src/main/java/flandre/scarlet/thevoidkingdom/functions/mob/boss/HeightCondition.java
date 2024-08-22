package flandre.scarlet.thevoidkingdom.functions.mob.boss;

import org.bukkit.Location;

public class HeightCondition implements TotemCondition {
    private final int maxY;
    private final int minY;

    public HeightCondition(int maxY, int minY) {
        this.maxY = maxY;
        this.minY = minY;
    }

    @Override
    public boolean isMeet(Location location) {
        int y = location.getBlockY();
        return y <= maxY && y >= minY;
    }


}

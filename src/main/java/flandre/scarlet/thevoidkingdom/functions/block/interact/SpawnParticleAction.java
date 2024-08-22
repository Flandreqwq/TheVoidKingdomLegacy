package flandre.scarlet.thevoidkingdom.functions.block.interact;

import org.bukkit.Location;
import org.bukkit.Particle;

public class SpawnParticleAction implements ResultAction {
    private final Particle particle;
    private final int amount;
    private final double xSpread;
    private final double ySpread;
    private final double zSpread;
    private final double speed;
    private final Object object;

    public SpawnParticleAction(Particle particle, int amount, double xSpread, double ySpread, double zSpread, double speed, Object object) {
        this.particle = particle;
        this.amount = amount;
        this.xSpread = xSpread;
        this.ySpread = ySpread;
        this.zSpread = zSpread;
        this.speed = speed;
        this.object = object;
    }

    @Override
    public String getResultNamespaceId() {
        return null;
    }

    @Override
    public void execute(Location location) {
        if (object == null) {
            location.getWorld().spawnParticle(particle, location.clone().add(0.5, 0.5, 0.5), amount, xSpread, ySpread, zSpread, speed);
        } else {
            location.getWorld().spawnParticle(particle, location.clone().add(0.5, 0.5, 0.5), amount, xSpread, ySpread, zSpread, speed, object);
        }
    }

    @Override
    public boolean isValid() {
        return true;
    }
}

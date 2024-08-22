package flandre.scarlet.thevoidkingdom.functions.block.interact;

import org.bukkit.Location;
import org.bukkit.Sound;

public class PlaySoundAction implements ResultAction {
    private final Sound sound;
    private final float volume;
    private final float pitch;

    public PlaySoundAction(Sound sound, float volume, float pitch) {
        this.sound = sound;
        this.volume = volume;
        this.pitch = pitch;
    }

    @Override
    public String getResultNamespaceId() {
        return null;
    }

    @Override
    public void execute(Location location) {
        location.getWorld().playSound(location.clone().add(0.5,0.5,0.5), sound, volume, pitch);
    }

    @Override
    public boolean isValid() {
        return true;
    }

    public Sound getSound() {
        return sound;
    }

    public float getVolume() {
        return volume;
    }

    public float getPitch() {
        return pitch;
    }
}

package flandre.scarlet.thevoidkingdom.functions.enchant;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class EnchantLock {
    public static final Set<EnchantLock> enchantLockSet = new HashSet<>();
    private Location location;
    private UUID uuid;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public static boolean hasLocation(Location location) {
        for (EnchantLock enchantLock : enchantLockSet) {
            if (enchantLock.getLocation().equals(location)) {
                return true;
            }
        }
        return false;
    }


    public static void addLock(Location location, Player player) {
        EnchantLock enchantLock = new EnchantLock(location, player.getUniqueId());
        enchantLockSet.add(enchantLock);
    }

    public static void removeLock(Location location) {
        enchantLockSet.removeIf(enchantLock -> enchantLock.getLocation().equals(location));
    }

    public static void removeLock(Player player) {
        UUID uuid1 = player.getUniqueId();
        enchantLockSet.removeIf(enchantLock -> enchantLock.getUuid().equals(uuid1));
    }

    public static boolean hasPlayer(Player player) {
        UUID uuid = player.getUniqueId();
        for (EnchantLock enchantLock : enchantLockSet) {
            if (enchantLock.getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    public static EnchantLock getByLocation(Location location) {
        for (EnchantLock enchantLock : enchantLockSet) {
            if (enchantLock.getLocation().equals(location)) {
                return enchantLock;
            }
        }
        return null;
    }

    public static EnchantLock getByPlayer(Player player) {
        UUID uuid = player.getUniqueId();
        for (EnchantLock enchantLock : enchantLockSet) {
            if (enchantLock.getUuid().equals(uuid)) {
                return enchantLock;
            }
        }
        return null;
    }

    public EnchantLock(Location location, UUID uuid) {
        this.location = location;
        this.uuid = uuid;
    }
}

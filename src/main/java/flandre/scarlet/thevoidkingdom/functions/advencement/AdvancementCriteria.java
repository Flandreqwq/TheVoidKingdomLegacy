package flandre.scarlet.thevoidkingdom.functions.advencement;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class AdvancementCriteria {
    private final NamespacedKey advancementKey;
    private final String criteria;
    private final Set<UUID> ignoredSet = new HashSet<>();

    public AdvancementCriteria(NamespacedKey advancementKey, String criteria) {
        this.advancementKey = advancementKey;
        this.criteria = criteria;
    }

    public NamespacedKey getAdvancementKey() {
        return advancementKey;
    }

    public String getCriteria() {
        return criteria;
    }

    public boolean isIgnoredPlayer(Player player) {
        return ignoredSet.contains(player.getUniqueId());
    }

    public void addIgnoredPlayer(Player player) {
        ignoredSet.add(player.getUniqueId());
    }
}

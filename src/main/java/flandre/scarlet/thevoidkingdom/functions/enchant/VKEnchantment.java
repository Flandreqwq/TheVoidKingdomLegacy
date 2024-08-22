package flandre.scarlet.thevoidkingdom.functions.enchant;

import java.util.HashSet;
import java.util.Set;

public class VKEnchantment {
    public static final Set<VKEnchantment> VK_ENCHANTMENTS = new HashSet<>();
    private final String enchantName;

    public VKEnchantment(String enchantName) {
        this.enchantName = enchantName;
    }

    public String getEnchantName() {
        return enchantName;
    }
}

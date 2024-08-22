package flandre.scarlet.thevoidkingdom.functions.enchant;

import org.bukkit.enchantments.Enchantment;

import java.util.List;

public class VanillaEnchantment extends VKEnchantment {
    public static final VanillaEnchantment KNOCKBACK = create("击退", Enchantment.KNOCKBACK,
            List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    public static final VanillaEnchantment FIRE_ASPECT = create("烈刃", Enchantment.FIRE_ASPECT,
            List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    public static final VanillaEnchantment ARROW_FIRE = create("火矢", Enchantment.ARROW_FIRE,
            List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    public static final VanillaEnchantment PIERCING = create("穿透", Enchantment.PIERCING,
            List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

    private final Enchantment enchantment;

    private final List<Integer> levelValues;

    public VanillaEnchantment(String enchantName, Enchantment enchantment, List<Integer> levelValues) {
        super(enchantName);
        this.enchantment = enchantment;
        this.levelValues = levelValues;
    }
    public static VanillaEnchantment create(String enchantName, Enchantment enchantment, List<Integer> levelValues) {
        VanillaEnchantment vanillaEnchantment = new VanillaEnchantment(enchantName, enchantment, levelValues);
        VK_ENCHANTMENTS.add(vanillaEnchantment);
        return vanillaEnchantment;
    }

    public Enchantment getEnchantment() {
        return enchantment;
    }

    public List<Integer> getLevelValues() {
        return levelValues;
    }
}

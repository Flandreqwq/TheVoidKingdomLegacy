package flandre.scarlet.thevoidkingdom.functions.enchant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record EnchantItemType(String miType, List<Card> allowedCard) {
    public static final Map<String, EnchantItemType> ENCHANT_ITEM_TYPE_MAP = new HashMap<>();
    public static final EnchantItemType TYPE_DAGGER = create("DAGGER", List.of(Card.ATTACK_DAMAGE_DAGGER, Card.CRITICAL_STRIKE_POWER, Card.CRITICAL_STRIKE_CHANCE, Card.MAX_DURABILITY, Card.DEFENSE, Card.MANA_REGENERATION, Card.HEALTH_REGENERATION));
    public static final EnchantItemType TYPE_SWORD = create("SWORD", List.of(Card.ATTACK_DAMAGE_SWORD, Card.CRITICAL_STRIKE_POWER, Card.CRITICAL_STRIKE_CHANCE, Card.MAX_DURABILITY, Card.DEFENSE, Card.MANA_REGENERATION, Card.HEALTH_REGENERATION, Card.SKILL_DAMAGE, Card.KNOCKBACK, Card.FIRE_ASPECT));
    public static final EnchantItemType TYPE_HAMMER = create("HAMMER", List.of(Card.ATTACK_DAMAGE_SWORD, Card.CRITICAL_STRIKE_POWER, Card.CRITICAL_STRIKE_CHANCE, Card.MAX_DURABILITY, Card.DEFENSE, Card.MANA_REGENERATION, Card.HEALTH_REGENERATION, Card.SKILL_DAMAGE, Card.BLUNT_POWER, Card.BLUNT_RATING));
    public static final EnchantItemType TYPE_BOW = create("BOW", List.of(Card.ATTACK_DAMAGE_BOW, Card.CRITICAL_STRIKE_POWER, Card.CRITICAL_STRIKE_CHANCE, Card.MAX_DURABILITY, Card.DEFENSE, Card.MANA_REGENERATION, Card.HEALTH_REGENERATION, Card.SKILL_DAMAGE, Card.ARROW_VELOCITY, Card.ARROW_FIRE));
    public static final EnchantItemType TYPE_CROSS = create("CROSS", List.of(Card.ATTACK_DAMAGE_BOW, Card.CRITICAL_STRIKE_POWER, Card.CRITICAL_STRIKE_CHANCE, Card.MAX_DURABILITY, Card.DEFENSE, Card.MANA_REGENERATION, Card.HEALTH_REGENERATION, Card.SKILL_DAMAGE, Card.ARROW_VELOCITY, Card.PIERCING));
    public static final EnchantItemType TYPE_SHOT = create("SHOT", List.of(Card.ATTACK_DAMAGE_SHOT, Card.CRITICAL_STRIKE_POWER, Card.CRITICAL_STRIKE_CHANCE, Card.MAX_DURABILITY, Card.DEFENSE, Card.MANA_REGENERATION, Card.HEALTH_REGENERATION, Card.SKILL_DAMAGE, Card.ARROW_VELOCITY, Card.DODGE_RATING));
    public static final EnchantItemType TYPE_WAND = create("WAND", List.of(Card.ATTACK_DAMAGE_WAND, Card.CRITICAL_STRIKE_POWER, Card.CRITICAL_STRIKE_CHANCE, Card.MAX_DURABILITY, Card.DEFENSE, Card.MANA_REGENERATION, Card.HEALTH_REGENERATION, Card.SKILL_DAMAGE, Card.MANA_COST));
    public static final EnchantItemType TYPE_LANTERN = create("LANTERN", List.of(Card.ATTACK_DAMAGE_LANTERN, Card.CRITICAL_STRIKE_POWER, Card.CRITICAL_STRIKE_CHANCE, Card.MAX_DURABILITY, Card.DEFENSE, Card.MANA_REGENERATION, Card.HEALTH_REGENERATION, Card.SKILL_DAMAGE, Card.MANA_COST));
    public static final EnchantItemType TYPE_BOOK = create("BOOK", List.of(Card.ATTACK_DAMAGE_WAND, Card.CRITICAL_STRIKE_POWER, Card.CRITICAL_STRIKE_CHANCE, Card.MAX_DURABILITY, Card.DEFENSE, Card.MANA_REGENERATION, Card.HEALTH_REGENERATION, Card.SKILL_DAMAGE, Card.MANA_COST));
    public static final EnchantItemType TYPE_ARMOR_WARRIOR = create("ARMOR_WARRIOR", List.of(Card.CRITICAL_STRIKE_POWER, Card.CRITICAL_STRIKE_CHANCE, Card.MAX_DURABILITY, Card.MAX_MANA, Card.MAX_HEALTH, Card.PHYSICAL_DAMAGE_REDUCTION, Card.MAGIC_DAMAGE_REDUCTION, Card.SKILL_DAMAGE));
    public static final EnchantItemType TYPE_ARMOR_ARCHER = create("ARMOR_ARCHER", List.of(Card.CRITICAL_STRIKE_POWER, Card.CRITICAL_STRIKE_CHANCE, Card.MAX_DURABILITY, Card.MAX_MANA, Card.DODGE_RATING, Card.PHYSICAL_DAMAGE_REDUCTION, Card.MAGIC_DAMAGE_REDUCTION, Card.SKILL_DAMAGE));
    public static final EnchantItemType TYPE_ARMOR_MAGE = create("ARMOR_MAGE", List.of(Card.CRITICAL_STRIKE_POWER, Card.CRITICAL_STRIKE_CHANCE, Card.MAX_DURABILITY, Card.MAX_MANA, Card.MAX_HEALTH, Card.PHYSICAL_DAMAGE_REDUCTION, Card.MAGIC_DAMAGE_REDUCTION, Card.SKILL_DAMAGE));


    public static EnchantItemType create(String miType, List<Card> allowedEnchantment) {
        EnchantItemType enchantItemType = new EnchantItemType(miType, allowedEnchantment);
        ENCHANT_ITEM_TYPE_MAP.put(miType, enchantItemType);
        return enchantItemType;
    }
}

package flandre.scarlet.thevoidkingdom.functions.enchant;

import flandre.scarlet.thevoidkingdom.utils.game.menu.MenuIcon;

import java.util.HashSet;
import java.util.Set;

public class Card {
    private final MenuIcon menuIcon;
    private final VKEnchantment vkEnchantment;
    public static final Set<Card> CARDS = new HashSet<>();
    public static final Card ATTACK_DAMAGE_DAGGER = create(EnchantMenuIcon.ATTACK_DAMAGE_WARRIOR, StatEnchantment.ATTACK_DAMAGE_DAGGER);
    public static final Card ATTACK_DAMAGE_SWORD = create(EnchantMenuIcon.ATTACK_DAMAGE_WARRIOR, StatEnchantment.ATTACK_DAMAGE_SWORD);
    public static final Card ATTACK_DAMAGE_BOW = create(EnchantMenuIcon.ATTACK_DAMAGE_ARCHER, StatEnchantment.ATTACK_DAMAGE_BOW);
    public static final Card ATTACK_DAMAGE_SHOT = create(EnchantMenuIcon.ATTACK_DAMAGE_ARCHER, StatEnchantment.ATTACK_DAMAGE_SHOT);
    public static final Card ATTACK_DAMAGE_WAND = create(EnchantMenuIcon.ATTACK_DAMAGE_MAGE, StatEnchantment.ATTACK_DAMAGE_WAND);
    public static final Card ATTACK_DAMAGE_LANTERN = create(EnchantMenuIcon.ATTACK_DAMAGE_MAGE, StatEnchantment.ATTACK_DAMAGE_LANTERN);
    public static final Card CRITICAL_STRIKE_POWER = create(EnchantMenuIcon.CRITICAL_STRIKE_POWER, StatEnchantment.CRITICAL_STRIKE_POWER);
    public static final Card CRITICAL_STRIKE_CHANCE = create(EnchantMenuIcon.CRITICAL_STRIKE_CHANCE, StatEnchantment.CRITICAL_STRIKE_CHANCE);
    public static final Card MAX_DURABILITY = create(EnchantMenuIcon.MAX_DURABILITY, StatEnchantment.MAX_DURABILITY);
    public static final Card DEFENSE = create(EnchantMenuIcon.DEFENSE, StatEnchantment.DEFENSE);
    public static final Card MANA_REGENERATION = create(EnchantMenuIcon.MANA_REGENERATION, StatEnchantment.MANA_REGENERATION);
    public static final Card HEALTH_REGENERATION = create(EnchantMenuIcon.HEALTH_REGENERATION, StatEnchantment.HEALTH_REGENERATION);
    public static final Card SKILL_DAMAGE = create(EnchantMenuIcon.SKILL_DAMAGE, StatEnchantment.SKILL_DAMAGE);
    public static final Card BLUNT_POWER = create(EnchantMenuIcon.BLUNT_POWER, StatEnchantment.BLUNT_POWER);
    public static final Card BLUNT_RATING = create(EnchantMenuIcon.BLUNT_RATING, StatEnchantment.BLUNT_RATING);
    public static final Card ARROW_VELOCITY = create(EnchantMenuIcon.ARROW_VELOCITY, StatEnchantment.ARROW_VELOCITY);
    public static final Card MANA_COST = create(EnchantMenuIcon.MANA_COST, StatEnchantment.MANA_COST);
    public static final Card DODGE_RATING = create(EnchantMenuIcon.DODGE_RATING, StatEnchantment.DODGE_RATING);
    public static final Card MAX_MANA = create(EnchantMenuIcon.MAX_MANA, StatEnchantment.MAX_MANA);
    public static final Card MAX_HEALTH = create(EnchantMenuIcon.MAX_HEALTH, StatEnchantment.MAX_HEALTH);
    public static final Card PHYSICAL_DAMAGE_REDUCTION = create(EnchantMenuIcon.PHYSICAL_DAMAGE_REDUCTION, StatEnchantment.PHYSICAL_DAMAGE_REDUCTION);
    public static final Card MAGIC_DAMAGE_REDUCTION = create(EnchantMenuIcon.MAGIC_DAMAGE_REDUCTION, StatEnchantment.MAGIC_DAMAGE_REDUCTION);
    public static final Card KNOCKBACK = create(EnchantMenuIcon.KNOCKBACK, VanillaEnchantment.KNOCKBACK);
    public static final Card FIRE_ASPECT = create(EnchantMenuIcon.FIRE_ASPECT, VanillaEnchantment.FIRE_ASPECT);
    public static final Card ARROW_FIRE = create(EnchantMenuIcon.ARROW_FIRE, VanillaEnchantment.ARROW_FIRE);
    public static final Card PIERCING = create(EnchantMenuIcon.PIERCING, VanillaEnchantment.PIERCING);
    public static final Card REALLOC = create(EnchantMenuIcon.REALLOC, null);
    public static final Card STOP = create(EnchantMenuIcon.STOP, null);
    public static final Card MORE_POINT_1 = create(EnchantMenuIcon.MORE_POINT_1, null);
    public static final Card MORE_POINT_2 = create(EnchantMenuIcon.MORE_POINT_2, null);
    public static final Card MORE_POINT_3 = create(EnchantMenuIcon.MORE_POINT_3, null);

    public Card(MenuIcon icon, VKEnchantment vkEnchantment) {
        this.menuIcon = icon;
        this.vkEnchantment = vkEnchantment;
    }

    public static Card create(MenuIcon icon, VKEnchantment vkEnchantment) {
        Card card = new Card(icon, vkEnchantment);
        CARDS.add(card);
        return card;
    }

    public MenuIcon getMenuIcon() {
        return menuIcon;
    }

    public VKEnchantment getVkEnchantment() {
        return vkEnchantment;
    }
}

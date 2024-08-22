package flandre.scarlet.thevoidkingdom.functions.enchant;

import java.util.List;

public class StatEnchantment extends VKEnchantment {
    public static final StatEnchantment ATTACK_DAMAGE_DAGGER = create("锋利", "ATTACK_DAMAGE",
            List.of(2.0, 3.0, 5.0, 7.0, 9.0, 11.0, 14.0, 17.0, 20.0, 23.0), Operation.ADD_STAT);
    public static final StatEnchantment ATTACK_DAMAGE_SWORD = create("锋利", "ATTACK_DAMAGE",
            List.of(3.0, 6.0, 10.0, 14.0, 18.0, 23.0, 28.0, 33.0, 38.0, 44.0), Operation.ADD_STAT);
    public static final StatEnchantment ATTACK_DAMAGE_BOW = create("利箭", "ATTACK_DAMAGE",
            List.of(4.0, 8.0, 12.0, 17.0, 22.0, 27.0, 32.0, 38.0, 44.0, 50.0), Operation.ADD_STAT);
    public static final StatEnchantment ATTACK_DAMAGE_SHOT = create("利箭", "ATTACK_DAMAGE",
            List.of(2.0, 3.0, 5.0, 7.0, 9.0, 11.0, 14.0, 17.0, 20.0, 23.0), Operation.ADD_STAT);
    public static final StatEnchantment ATTACK_DAMAGE_WAND = create("咒术", "ATTACK_DAMAGE",
            List.of(4.0, 8.0, 12.0, 17.0, 22.0, 27.0, 32.0, 38.0, 44.0, 50.0), Operation.ADD_STAT);
    public static final StatEnchantment ATTACK_DAMAGE_LANTERN = create("咒术", "ATTACK_DAMAGE",
            List.of(3.0, 6.0, 10.0, 14.0, 18.0, 23.0, 28.0, 33.0, 38.0, 44.0), Operation.ADD_STAT);
    public static final StatEnchantment CRITICAL_STRIKE_POWER = create("会心", "CRITICAL_STRIKE_POWER",
            List.of(3.0, 6.0, 10.0, 14.0, 18.0, 23.0, 28.0, 33.0, 38.0, 44.0), Operation.ADD_STAT);
    public static final StatEnchantment CRITICAL_STRIKE_CHANCE = create("幸运", "CRITICAL_STRIKE_CHANCE",
            List.of(2.0, 3.0, 5.0, 7.0, 9.0, 11.0, 14.0, 17.0, 20.0, 23.0), Operation.ADD_STAT);
    public static final StatEnchantment MAX_DURABILITY = create("耐久", "MAX_DURABILITY",
            List.of(1.2, 1.5, 2.0, 2.5, 3.0, 4.0, 5.0, 7.0, 9.0, 11.0), Operation.DURABILITY);
    public static final StatEnchantment DEFENSE = create("抵挡", "DEFENSE",
            List.of(3.0, 6.0, 10.0, 14.0, 18.0, 23.0, 28.0, 33.0, 38.0, 44.0), Operation.ADD_STAT);
    public static final StatEnchantment MANA_REGENERATION = create("魔涌", "MANA_REGENERATION",
            List.of(0.5, 1.0, 1.5, 2.0, 3.0, 4.0, 5.0, 6.0, 8.0, 10.0), Operation.ADD_STAT);
    public static final StatEnchantment HEALTH_REGENERATION = create("回天", "HEALTH_REGENERATION",
            List.of(0.3, 0.5, 0.7, 1.0, 1.3, 1.6, 1.9, 2.4, 2.9, 3.4), Operation.ADD_STAT);
    public static final StatEnchantment SKILL_DAMAGE = create("灵击", "SKILL_DAMAGE",
            List.of(3.0, 6.0, 10.0, 14.0, 18.0, 23.0, 28.0, 33.0, 38.0, 44.0), Operation.ADD_STAT);
    public static final StatEnchantment BLUNT_POWER = create("巨大", "BLUNT_POWER",
            List.of(0.4, 0.7, 1.0, 1.4, 1.8, 2.2, 2.7, 3.2, 3.7, 4.2), Operation.ADD_STAT);
    public static final StatEnchantment BLUNT_RATING = create("沉重", "BLUNT_RATING",
            List.of(5.0, 10.0, 15.0, 20.0, 25.0, 30.0, 35.0, 40.0, 45.0, 50.0), Operation.ADD_STAT);
    public static final StatEnchantment ARROW_VELOCITY = create("流矢", "ARROW_VELOCITY",
            List.of(0.1, 0.2, 0.3, 0.4, 0.5, 0.7, 0.9, 1.1, 1.3, 1.5), Operation.ADD_STAT);
    public static final StatEnchantment MANA_COST = create("节约", "MANA_COST",
            List.of(0.95, 0.9, 0.85, 0.8, 0.75, 0.7, 0.65, 0.6, 0.55, 0.5), Operation.MULTIPLY_STAT);
    public static final StatEnchantment DODGE_RATING = create("灵活", "DODGE_RATING",
            List.of(2.0, 4.0, 6.0, 8.0, 10.0, 12.0, 14.0, 16.0, 18.0, 20.0), Operation.ADD_STAT);
    public static final StatEnchantment MAX_MANA = create("充沛", "MAX_MANA",
            List.of(10.0, 20.0, 30.0, 40.0, 50.0, 60.0, 70.0, 80.0, 90.0, 100.0), Operation.ADD_STAT);
    public static final StatEnchantment MAX_HEALTH = create("底力", "MAX_HEALTH",
            List.of(4.0, 8.0, 12.0, 16.0, 20.0, 24.0, 28.0, 32.0, 36.0, 40.0), Operation.ADD_STAT);
    public static final StatEnchantment PHYSICAL_DAMAGE_REDUCTION = create("坚韧", "PHYSICAL_DAMAGE_REDUCTION",
            List.of(3.0, 6.0, 9.0, 12.0, 15.0, 18.0, 21.0, 24.0, 27.0, 30.0), Operation.ADD_STAT);
    public static final StatEnchantment MAGIC_DAMAGE_REDUCTION = create("咒御", "MAGIC_DAMAGE_REDUCTION",
            List.of(3.0, 6.0, 9.0, 12.0, 15.0, 18.0, 21.0, 24.0, 27.0, 30.0), Operation.ADD_STAT);
    private final String statName;
    private final List<Double> statValues;
    private final Operation operation;


    public StatEnchantment(String enchantName, String statName, List<Double> statValues, Operation operation) {
        super(enchantName);
        this.statName = statName;
        this.statValues = statValues;
        this.operation = operation;
    }

    public static StatEnchantment create(String enchantName, String statName, List<Double> statValues, Operation operation) {
        StatEnchantment statEnchantment = new StatEnchantment(enchantName, statName, statValues, operation);
        VK_ENCHANTMENTS.add(statEnchantment);
        return statEnchantment;
    }

    public String getStatName() {
        return statName;
    }

    public List<Double> getStatValues() {
        return statValues;
    }

    public Operation getOperation() {
        return operation;
    }

    public enum Operation {
        ADD_STAT,
        MULTIPLY_STAT,
        DURABILITY
    }
}

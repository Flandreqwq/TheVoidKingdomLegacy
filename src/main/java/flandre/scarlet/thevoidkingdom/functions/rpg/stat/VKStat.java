package flandre.scarlet.thevoidkingdom.functions.rpg.stat;

public abstract class VKStat {
    //    public final statFormat
    public final VKStatType statType;
    public final double value;
    public final String nbtKey;

    public VKStat(VKStatType statType, double value, String nbtKey) {
        this.statType = statType;
        this.value = value;
        this.nbtKey = nbtKey;
    }

}

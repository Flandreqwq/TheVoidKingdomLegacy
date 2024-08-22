public class EnchantList {
    int[] levelLow;
    int[] levelMiddle;
    int[] levelHigh;

    public EnchantList(int[] levelLow, int[] levelMiddle, int[] levelHigh) {
        this.levelLow = levelLow;
        this.levelMiddle = levelMiddle;
        this.levelHigh = levelHigh;
    }

    public int[] getLevelLow() {
        return levelLow;
    }

    public int[] getLevelMiddle() {
        return levelMiddle;
    }

    public int[] getLevelHigh() {
        return levelHigh;
    }
}

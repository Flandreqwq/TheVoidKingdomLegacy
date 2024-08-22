package flandre.scarlet.thevoidkingdom.functions.rpg.stat.number;

import java.util.Random;

public class BinomialIntegerProvider implements IntegerProvider {

    private final int n;
    private final double p;

    private final Random random;

    public BinomialIntegerProvider(int n, double p) {
        this.n = n;
        this.p = p;
        this.random = new Random();
    }

    public int generateNumber() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (random.nextDouble() < p) {
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean isValid() {
        return n >= 0 && p >= 0 && p <= 1;
    }
}

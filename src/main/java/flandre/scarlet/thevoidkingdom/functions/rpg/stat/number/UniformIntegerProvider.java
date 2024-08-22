package flandre.scarlet.thevoidkingdom.functions.rpg.stat.number;

import java.util.Random;

public class UniformIntegerProvider implements IntegerProvider {

    private final int max;
    private final int min;

    private final Random random;

    public UniformIntegerProvider(int max, int min) {
        this.max = max;
        this.min = min;
        this.random = new Random();
    }

    public int generateNumber() {
        return random.nextInt(max - min + 1) + min;
    }

    @Override
    public boolean isValid() {
        return max >= min;
    }
}

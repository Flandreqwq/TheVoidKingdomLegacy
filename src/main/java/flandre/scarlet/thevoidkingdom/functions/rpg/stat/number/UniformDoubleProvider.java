package flandre.scarlet.thevoidkingdom.functions.rpg.stat.number;

import java.util.Random;

public class UniformDoubleProvider implements DoubleProvider {

    private final double max;
    private final double min;

    private final Random random;

    public UniformDoubleProvider(double max, double min) {
        this.max = max;
        this.min = min;
        this.random = new Random();
    }

    public double generateNumber() {
        return random.nextDouble() * (max - min) + min;
    }

    @Override
    public boolean isValid() {
        return max >= min;
    }
}

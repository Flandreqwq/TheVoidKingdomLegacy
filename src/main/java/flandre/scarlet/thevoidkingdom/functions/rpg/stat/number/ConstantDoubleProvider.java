package flandre.scarlet.thevoidkingdom.functions.rpg.stat.number;

public class ConstantDoubleProvider implements DoubleProvider {

    private final double constant;

    public ConstantDoubleProvider(double constant) {
        this.constant = constant;
    }

    public double generateNumber() {
        return constant;
    }

    @Override
    public boolean isValid() {
        return true;
    }
}

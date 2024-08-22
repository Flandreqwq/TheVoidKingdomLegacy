package flandre.scarlet.thevoidkingdom.utils.game;

import java.util.HashMap;
import java.util.Map;

public class CoolDownMap<T> {
    private final int coolDownMillis;
    private final Map<T, Long> map = new HashMap<>();

    public CoolDownMap(int coolDownMillis) {
        this.coolDownMillis = coolDownMillis;
    }

    public boolean isCoolDown(T t) {
        return System.currentTimeMillis() - map.getOrDefault(t, 0L) < coolDownMillis;
    }

    public void add(T t) {
        map.put(t, System.currentTimeMillis());
    }
}

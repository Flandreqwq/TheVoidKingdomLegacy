package flandre.scarlet.thevoidkingdom.utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CountMap<T> {
    private final long timeout;

    private final Map<T, LinkedList<Long>> map = new HashMap<>();

    public CountMap(long timeout) {
        this.timeout = timeout;
    }

    public int count(T t) {
        if (!map.containsKey(t)) {
            return 0;
        }
        LinkedList<Long> longs = map.get(t);
        while (!longs.isEmpty() && longs.get(0) < System.currentTimeMillis() - timeout) {
            longs.removeFirst();
        }

        return longs.size();
    }

    public void add(T t) {
        if (!map.containsKey(t)) {
            map.put(t, new LinkedList<>());
        }
        map.get(t).add(System.currentTimeMillis());
    }
}
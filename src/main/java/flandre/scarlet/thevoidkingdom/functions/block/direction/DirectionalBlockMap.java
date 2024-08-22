package flandre.scarlet.thevoidkingdom.functions.block.direction;

import java.util.HashMap;
import java.util.Map;

public class DirectionalBlockMap {
    private final Map<String, DirectionalBlock> directionalBlockKeyMap = new HashMap<>();
    private final Map<String, String> originNamespaceIdMap = new HashMap<>();

    private int reloadCount = 0;

    public void clear() {
        if (reloadCount == 0) {
            directionalBlockKeyMap.clear();
            originNamespaceIdMap.clear();
        } else if (reloadCount >= 2 || reloadCount < 0) {
            reloadCount = 0;
            return;
        }
        reloadCount++;
    }

    public void addKey(String namespaceId, DirectionalBlock directionalBlock) {
        directionalBlockKeyMap.put(namespaceId, directionalBlock);
    }

    public void addOrigin(String namespaceId, String originNamespaceId) {
        originNamespaceIdMap.put(namespaceId, originNamespaceId);
    }


    public DirectionalBlock getDirectionalBlock(String namespaceId){
        return directionalBlockKeyMap.get(namespaceId);
    }

    public String getOrigin(String namespaceId){
        return originNamespaceIdMap.get(namespaceId);
    }

}

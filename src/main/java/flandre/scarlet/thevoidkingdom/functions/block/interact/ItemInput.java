package flandre.scarlet.thevoidkingdom.functions.block.interact;

public class ItemInput implements InputChoice{
    private final String namespaceId;

    public ItemInput(String namespaceId) {
        this.namespaceId = namespaceId;
    }

    @Override
    public String getInputNamespaceId() {
        return namespaceId;
    }

    @Override
    public String getInputKey() {
        return namespaceId;
    }
}

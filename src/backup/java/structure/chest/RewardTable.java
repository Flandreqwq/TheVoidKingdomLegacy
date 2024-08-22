package flandre.scarlet.thevoidkingdom.functions.structure.chest;

import dev.lone.itemsadder.api.CustomStack;
import flandre.scarlet.thevoidkingdom.TheVoidKingdom;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public final class RewardTable {
    private final List<String> normalItems;
    private final List<String> rareItems;
    private final List<String> treasureItems;
    private final List<ItemStack> normalItemStacks = new ArrayList<>();
    private final List<ItemStack> rareItemStacks = new ArrayList<>();
    private final List<ItemStack> treasureItemStacks = new ArrayList<>();
    private final int normalStackMinNumber;
    private final int normalStackMaxNumber;
    private final int normalMaxAmount;
    private final int rareStackMinNumber;
    private final int rareStackMaxNumber;
    private final int rareMaxAmount;
    private final double treasurePossibility;
    private final int treasureMaxAmount;
    private final List<Integer> chestSlots = List.of(
            0, 1, 2, 3, 4, 5, 6, 7, 8,
            9, 10, 11, 12, 13, 14, 15, 16, 17,
            18, 19, 20, 21, 22, 23, 24, 25, 26
    );

    public RewardTable(List<String> normalItems, List<String> rareItems, List<String> treasureItems, int normalStackMinNumber, int normalStackMaxNumber, int normalMaxAmount, int rareStackMinNumber, int rareStackMaxNumber, int rareMaxAmount, double treasurePossibility, int treasureMaxAmount) {
        this.normalItems = normalItems;
        this.rareItems = rareItems;
        this.treasureItems = treasureItems;
        this.normalStackMinNumber = normalStackMinNumber;
        this.normalStackMaxNumber = normalStackMaxNumber;
        this.normalMaxAmount = normalMaxAmount;
        this.rareStackMinNumber = rareStackMinNumber;
        this.rareStackMaxNumber = rareStackMaxNumber;
        this.rareMaxAmount = rareMaxAmount;
        this.treasurePossibility = treasurePossibility;
        this.treasureMaxAmount = treasureMaxAmount;
    }

    private void GetItemStacks(List<String> stringList, List<ItemStack> itemStackList) {
        if (!itemStackList.isEmpty()) {
            return;
        }
        for (String str : stringList) {
            if (str.contains(":")) {
                CustomStack stack = CustomStack.getInstance(str);
                if (stack != null) {
                    itemStackList.add(stack.getItemStack());
                } else {
                    TheVoidKingdom.LOGGER.warning("没有找到命名空间为 " + str + " 的ia物品，无法将该箱子战利品置入表中！");
                }
            } else {
                Material material = Material.getMaterial(str);
                if (material != null) {
                    itemStackList.add(new ItemStack(material));
                } else {
                    TheVoidKingdom.LOGGER.warning("Material " + str + " 不存在，无法将该箱子战利品置入表中！");
                }
            }
        }
    }

    public List<ItemStack> getRandomResult() {
        List<ItemStack> resultItemStacks = new ArrayList<>();
        GetItemStacks(normalItems, normalItemStacks);
        GetItemStacks(rareItems, rareItemStacks);
        GetItemStacks(treasureItems, treasureItemStacks);
        Random random = new Random();
        Random random2 = new Random();
        Random random3 = new Random();
        int size = normalItemStacks.size();
        for (int i = random.nextInt(normalStackMaxNumber - normalStackMinNumber + 1) + normalStackMinNumber; i > 0; i--) {
            ItemStack itemStack = normalItemStacks.get(random2.nextInt(size));
            itemStack.setAmount(random3.nextInt(normalMaxAmount) + 1);
            resultItemStacks.add(itemStack);
        }
        int size2 = rareItemStacks.size();
        for (int i = random.nextInt(rareStackMaxNumber - rareStackMinNumber + 1) + rareStackMinNumber; i > 0; i--) {
            ItemStack itemStack = rareItemStacks.get(random2.nextInt(size2));
            itemStack.setAmount(random3.nextInt(rareMaxAmount) + 1);
            resultItemStacks.add(itemStack);
        }
        if (random.nextDouble() < treasurePossibility) {
            int size3 = treasureItems.size();
            ItemStack itemStack = treasureItemStacks.get(random2.nextInt(size3));
            itemStack.setAmount(random3.nextInt(treasureMaxAmount) + 1);
            resultItemStacks.add(itemStack);
        }
        return resultItemStacks;
    }

    public void fillChest(Inventory inventory) {
        List<Integer> chestSlots1 = new ArrayList<>(chestSlots);
        Collections.shuffle(chestSlots1);
        List<Integer> resultSlots = new ArrayList<>();
        List<ItemStack> resultItemStacks = this.getRandomResult();
        int resultAmount = resultItemStacks.size();
        if (resultAmount > 27) {
            TheVoidKingdom.LOGGER.warning("意外生成了多于27叠的战利品，无法置入箱子中！");
            return;
        }
        for (int i = 0; i < resultAmount; i++) {
            resultSlots.add(chestSlots1.get(i));
        }
        for (int i = 0; i < resultAmount; i++) {
            inventory.setItem(resultSlots.get(i), resultItemStacks.get(i));
        }
    }
}


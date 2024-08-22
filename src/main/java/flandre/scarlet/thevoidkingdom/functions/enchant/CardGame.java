package flandre.scarlet.thevoidkingdom.functions.enchant;

import java.util.*;

public class CardGame {
    public int point;
    public List<Card> cardResultList = new ArrayList<>();
    public List<Boolean> cardStateList;
    private final EnchantItemType enchantItemType;
    private final CardGameInfo cardGameInfo;


    private void addCardToGame(Card card, int amount) {
        for (int i = amount; i > 0; i--) {
            this.cardResultList.add(card);
        }
    }

    public CardGame(EnchantItemType enchantItemType, CardGameInfo cardGameInfo) {
        this.point = cardGameInfo.getPoint();
        this.enchantItemType = enchantItemType;
        this.cardGameInfo = cardGameInfo;
        this.cardStateList = Arrays.asList(
                false, false, false, false, false, false,
                false, false, false, false, false, false,
                false, false, false, false, false, false,
                false, false, false, false, false, false,
                false, false, false, false, false, false,
                false, false, false, false, false, false
        );
        int enchantmentAmount = cardGameInfo.getEnchantmentAmount();
        int reallocAmount = cardGameInfo.getReallocAmount();
        int stopAmount = cardGameInfo.getStopAmount();
        int morePointAmount1 = cardGameInfo.getMorePointAmount1();
        int morePointAmount2 = cardGameInfo.getMorePointAmount2();
        int morePointAmount3 = cardGameInfo.getMorePointAmount3();
        addCardToGame(Card.REALLOC, reallocAmount);
        addCardToGame(Card.STOP, stopAmount);
        addCardToGame(Card.MORE_POINT_1, morePointAmount1);
        addCardToGame(Card.MORE_POINT_2, morePointAmount2);
        addCardToGame(Card.MORE_POINT_3, morePointAmount3);
        int otherCardTotalAmount = (36 - reallocAmount - stopAmount - morePointAmount1 - morePointAmount2 - morePointAmount3);
        List<Card> allowedEnchantmentsClone = new LinkedList<>(enchantItemType.allowedCard());
        List<Card> selectCards = new ArrayList<>();
        Random random = new Random();
        for (int i = enchantmentAmount; i > 0; i--) {
            if (allowedEnchantmentsClone.isEmpty()) {
                break;
            }
            Card enchantment = allowedEnchantmentsClone.get(random.nextInt(allowedEnchantmentsClone.size()));
            selectCards.add(enchantment);
            allowedEnchantmentsClone.remove(enchantment);
        }
        if (selectCards.isEmpty()) {
            //无可抽取附魔，就全放精神涣散
            addCardToGame(Card.STOP, otherCardTotalAmount);
        } else {
            for (int i = otherCardTotalAmount / 2; i > 0; i--) {
                Card card = selectCards.get(random.nextInt(enchantmentAmount));
                addCardToGame(card, 2);
            }
        }
        //打乱
        Collections.shuffle(this.cardResultList);
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public List<Card> getCardResultList() {
        return cardResultList;
    }

    public List<Boolean> getCardStateList() {
        return cardStateList;
    }

    public EnchantItemType getEnchantItem() {
        return enchantItemType;
    }

    public CardGameInfo getCardGameInfo() {
        return cardGameInfo;
    }

    public int getOpenCardAmount() {
        int count = 0;
        for (Boolean b : cardStateList) {
            if (b) {
                count++;
            }
        }
        return count;
    }

    public int getClosedCardAmount() {
        return 36 - getOpenCardAmount();
    }
}

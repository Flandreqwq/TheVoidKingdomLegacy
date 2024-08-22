package flandre.scarlet.thevoidkingdom.functions.enchant;

import java.util.Random;

public class CardGameInfo {
    private int point;
    private int maxLevel;
    private int enchantmentAmount;
    private int reallocAmount;
    private int stopAmount;
    private int morePointAmount1;
    private int morePointAmount2;
    private int morePointAmount3;
    private double totalIdeaAmount;
    private double totalInspirationAmount;
    private double totalRankAmount;
    private int expDiscount;

    public CardGameInfo(double totalIdeaAmount, double totalInspirationAmount, double totalRankAmount, int expDiscount, double stopPossible) {
        this.totalIdeaAmount = totalIdeaAmount;
        this.totalInspirationAmount = totalInspirationAmount;
        this.totalRankAmount = totalRankAmount;
        this.point = (int) totalIdeaAmount;
        this.maxLevel = (int) totalRankAmount;
        this.enchantmentAmount = 3;
        this.reallocAmount = 0;
        this.stopAmount = 0;
        this.morePointAmount1 = 0;
        this.morePointAmount2 = 0;
        this.morePointAmount3 = 0;
        this.expDiscount = expDiscount;
        Random random = new Random();
        //计算思绪点数量
        if (random.nextDouble() < totalIdeaAmount - point) {
            point += 1;
        }
        point = point == 0 ? 1 : point;
        //计算新的开始数量
        if (random.nextDouble() < Math.min(totalInspirationAmount * 0.005, 0.5)) {
            reallocAmount = 2;
        }
        //计算精神涣散数量
        double var0 = Math.max(1.0 - stopPossible, 0.0);
        while (var0 > 1.0) {
            stopAmount += 2;
            var0 -= 1.0;
        }
        if (random.nextDouble() < var0) {
            stopAmount += 2;
        }
        stopAmount = Math.min(stopAmount, 18);
        //计算灵光一闪数量
        double var1 = Math.min(totalInspirationAmount * 3, 300.0);
        if (var1 > 200.0) {
            morePointAmount1 += 2;
            var1 -= 100.0;
        }
        if (var1 > 100.0) {
            morePointAmount1 += 2;
            var1 -= 100.0;
        }
        if (random.nextDouble() < var1 / 100.0) {
            morePointAmount1 += 2;
        }
        //计算茅塞顿开数量
        double var2 = Math.min(totalInspirationAmount, 200.0);
        if (var2 > 100.0) {
            morePointAmount2 += 2;
            var2 -= 100.0;
        }
        if (random.nextDouble() < var2 / 100.0) {
            morePointAmount2 += 2;
        }
        //计算醍醐灌顶数量
        if (random.nextDouble() < Math.min(totalInspirationAmount * 0.0025, 1)) {
            morePointAmount3 = 2;
        }
        System.out.println("point: " + point);
        System.out.println("maxLevel: " + maxLevel);
        System.out.println("reallocAmount: " + reallocAmount);
        System.out.println("stopAmount: " + stopAmount);
        System.out.println("more1: " + morePointAmount1);
        System.out.println("more2: " + morePointAmount2);
        System.out.println("more3: " + morePointAmount3);
    }

    public CardGameInfo(double totalIdeaAmount, double totalInspirationAmount, double totalRankAmount, int point, int maxLevel, int enchantmentAmount, int reallocAmount, int stopAmount, int morePointAmount1, int morePointAmount2, int morePointAmount3) {
        this.point = point;
        this.maxLevel = maxLevel;
        this.enchantmentAmount = enchantmentAmount;
        this.reallocAmount = reallocAmount;
        this.stopAmount = stopAmount;
        this.morePointAmount1 = morePointAmount1;
        this.morePointAmount2 = morePointAmount2;
        this.morePointAmount3 = morePointAmount3;
        this.totalIdeaAmount = totalIdeaAmount;
        this.totalInspirationAmount = totalInspirationAmount;
        this.totalRankAmount = totalRankAmount;
    }

    public int getRequireExp() {
        return Math.max(((int) (totalIdeaAmount + totalInspirationAmount + totalRankAmount * 10)) - expDiscount, 1);
    }

    public int getPoint() {
        return point;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getEnchantmentAmount() {
        return enchantmentAmount;
    }

    public int getReallocAmount() {
        return reallocAmount;
    }

    public int getStopAmount() {
        return stopAmount;
    }

    public int getMorePointAmount1() {
        return morePointAmount1;
    }

    public int getMorePointAmount2() {
        return morePointAmount2;
    }

    public int getMorePointAmount3() {
        return morePointAmount3;
    }

    public double getTotalIdeaAmount() {
        return totalIdeaAmount;
    }

    public double getTotalInspirationAmount() {
        return totalInspirationAmount;
    }

    public double getTotalRankAmount() {
        return totalRankAmount;
    }

    public int getExpDiscount() {
        return expDiscount;
    }

    public void setExpDiscount(int expDiscount) {
        this.expDiscount = expDiscount;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public void setEnchantmentAmount(int enchantmentAmount) {
        this.enchantmentAmount = enchantmentAmount;
    }

    public void setReallocAmount(int reallocAmount) {
        this.reallocAmount = reallocAmount;
    }

    public void setStopAmount(int stopAmount) {
        this.stopAmount = stopAmount;
    }

    public void setMorePointAmount1(int morePointAmount1) {
        this.morePointAmount1 = morePointAmount1;
    }

    public void setMorePointAmount2(int morePointAmount2) {
        this.morePointAmount2 = morePointAmount2;
    }

    public void setMorePointAmount3(int morePointAmount3) {
        this.morePointAmount3 = morePointAmount3;
    }

    public void setTotalIdeaAmount(double totalIdeaAmount) {
        this.totalIdeaAmount = totalIdeaAmount;
    }

    public void setTotalInspirationAmount(double totalInspirationAmount) {
        this.totalInspirationAmount = totalInspirationAmount;
    }

    public void setTotalRankAmount(double totalRankAmount) {
        this.totalRankAmount = totalRankAmount;
    }
}

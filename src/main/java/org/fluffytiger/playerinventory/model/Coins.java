package org.fluffytiger.playerinventory.model;

public class Coins {
    private int gold;
    private int silver;

    public Coins(int gold, int silver) {
        this.gold = gold;
        this.silver = silver;
    }

    public void subtract(Coins amount) {
        this.gold -= amount.gold;
        this.silver -= amount.silver;
    }

    public int getGold() {
        return gold;
    }

    public int getSilver() {
        return silver;
    }
}

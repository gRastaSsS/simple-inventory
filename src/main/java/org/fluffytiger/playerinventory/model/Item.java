package org.fluffytiger.playerinventory.model;

public class Item {
    private final String id;
    private final Coins price;

    public Item(String id, Coins price) {
        this.id = id;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public Coins getPrice() {
        return price;
    }
}

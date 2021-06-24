package org.fluffytiger.playerinventory.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class PurchaseRequest {
    private final String playerId;
    private final String itemId;

    @JsonCreator
    public PurchaseRequest(String playerId, String itemId) {
        this.playerId = playerId;
        this.itemId = itemId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getItemId() {
        return itemId;
    }
}

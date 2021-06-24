package org.fluffytiger.playerinventory.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class TransferRequest {
    private final String fromPlayerId;
    private final String toPlayerId;
    private final String itemId;

    @JsonCreator
    public TransferRequest(String fromPlayerId, String toPlayerId, String itemId) {
        this.fromPlayerId = fromPlayerId;
        this.toPlayerId = toPlayerId;
        this.itemId = itemId;
    }

    public String getFromPlayerId() {
        return fromPlayerId;
    }

    public String getToPlayerId() {
        return toPlayerId;
    }

    public String getItemId() {
        return itemId;
    }
}

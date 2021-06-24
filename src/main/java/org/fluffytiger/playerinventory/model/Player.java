package org.fluffytiger.playerinventory.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Player {
    private final String id;

    @JsonCreator
    public Player(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

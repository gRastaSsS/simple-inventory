package org.fluffytiger.playerinventory.controller;

import org.fluffytiger.playerinventory.model.Item;
import org.fluffytiger.playerinventory.model.Player;
import org.fluffytiger.playerinventory.model.PurchaseRequest;
import org.fluffytiger.playerinventory.model.PurchaseResponse;
import org.fluffytiger.playerinventory.model.TransferRequest;
import org.fluffytiger.playerinventory.model.TransferResponse;
import org.fluffytiger.playerinventory.service.ItemService;
import org.fluffytiger.playerinventory.service.PlayerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ApplicationController {
    private final PlayerService playerService;
    private final ItemService itemService;

    public ApplicationController(PlayerService playerService, ItemService itemService) {
        this.playerService = playerService;
        this.itemService = itemService;
    }

    @PostMapping("/player")
    public void createPlayer(@RequestBody Player player) {
        this.playerService.create(player);
    }

    @PostMapping("/item")
    public void createItem(@RequestBody Item item) {
        this.itemService.create(item);
    }

    @PostMapping("/inventory/purchase")
    public PurchaseResponse purchaseItem(@RequestBody PurchaseRequest body) {
        try {
            return this.itemService.purchase(body.getPlayerId(), body.getItemId());
        } catch (RuntimeException e) {
            return new PurchaseResponse(PurchaseResponse.Status.UNEXPECTED_ERROR, e.getMessage());
        }
    }

    @PostMapping("/inventory/transfer")
    public TransferResponse transferItem(@RequestBody TransferRequest body) {
        try {
            return this.itemService.transfer(
                body.getFromPlayerId(),
                body.getToPlayerId(),
                body.getItemId()
            );
        } catch (RuntimeException e) {
            return new TransferResponse(TransferResponse.Status.UNEXPECTED_ERROR, e.getMessage());
        }
    }
}

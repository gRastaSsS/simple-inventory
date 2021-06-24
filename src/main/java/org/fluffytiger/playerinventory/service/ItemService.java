package org.fluffytiger.playerinventory.service;

import org.fluffytiger.playerinventory.dao.ItemDAO;
import org.fluffytiger.playerinventory.dao.PlayerBalanceDAO;
import org.fluffytiger.playerinventory.dao.PlayerInventoryDAO;
import org.fluffytiger.playerinventory.model.Coins;
import org.fluffytiger.playerinventory.model.Item;
import org.fluffytiger.playerinventory.model.PurchaseResponse;
import org.fluffytiger.playerinventory.model.TransferResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemService {
    private final PlayerBalanceDAO playerBalanceDAO;
    private final PlayerInventoryDAO playerInventoryDAO;
    private final ItemDAO itemDAO;

    public ItemService(
        PlayerBalanceDAO playerBalanceDAO,
        PlayerInventoryDAO playerInventoryDAO,
        ItemDAO itemDAO
    ) {
        this.playerBalanceDAO = playerBalanceDAO;
        this.playerInventoryDAO = playerInventoryDAO;
        this.itemDAO = itemDAO;
    }

    @Transactional
    public void create(Item item) {
        itemDAO.insert(item);
    }

    @Transactional
    public TransferResponse transfer(String fromId, String toId, String itemId) {
        if (fromId.equals(toId)) {
            return new TransferResponse(TransferResponse.Status.SAME_PLAYER);
        }

        boolean isRemoved = playerInventoryDAO.removeItem(fromId, itemId);
        if (!isRemoved) {
            return new TransferResponse(TransferResponse.Status.NO_SUCH_ITEM);
        }

        playerInventoryDAO.addItem(toId, itemId);
        return TransferResponse.SUCCESS_RESPONSE;
    }

    @Transactional
    public PurchaseResponse purchase(String playerId, String itemId) {
        Coins balance = playerBalanceDAO.get(playerId, true);
        Item item = itemDAO.get(itemId);

        if (!playerCanBuyItem(item.getPrice(), balance)) {
            return new PurchaseResponse(PurchaseResponse.Status.INSUFFICIENT_FUNDS);
        }

        balance.subtract(item.getPrice());
        playerInventoryDAO.addItem(playerId, item.getId());
        playerBalanceDAO.update(playerId, balance);
        return PurchaseResponse.SUCCESS_RESPONSE;
    }

    private boolean playerCanBuyItem(Coins price, Coins balance) {
        boolean enoughGold = balance.getGold() >= price.getGold();
        boolean enoughSilver = balance.getSilver() >= price.getSilver();
        return enoughGold && enoughSilver;
    }
}

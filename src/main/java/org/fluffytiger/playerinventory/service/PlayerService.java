package org.fluffytiger.playerinventory.service;

import org.fluffytiger.playerinventory.dao.PlayerBalanceDAO;
import org.fluffytiger.playerinventory.dao.PlayerDAO;
import org.fluffytiger.playerinventory.model.Coins;
import org.fluffytiger.playerinventory.model.Player;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerService {
    private final PlayerBalanceDAO playerBalanceDAO;
    private final PlayerDAO playerDAO;

    public PlayerService(PlayerBalanceDAO playerBalanceDAO, PlayerDAO playerDAO) {
        this.playerBalanceDAO = playerBalanceDAO;
        this.playerDAO = playerDAO;
    }

    @Transactional
    public void create(Player player) {
        playerDAO.insert(player);
        playerBalanceDAO.insert(player.getId(), new Coins(100000, 100000));
    }
}

package org.fluffytiger.playerinventory.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerInventoryDAO {
    private final JdbcTemplate jdbcTemplate;

    public PlayerInventoryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean removeItem(String playerId, String itemId) {
        int result = jdbcTemplate.update(
            "UPDATE PlayerInventory SET amount = amount - 1 WHERE playerId = ? and itemId = ? and amount > 0",
            ps -> {
                ps.setString(1, playerId);
                ps.setString(2, itemId);
            }
        );

        return result > 0;
    }

    public void addItem(String playerId, String itemId) {
        jdbcTemplate.update(
            "INSERT INTO PlayerInventory VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE amount = amount + 1",
            ps -> {
                ps.setString(1, playerId);
                ps.setString(2, itemId);
                ps.setInt(3, 1);
            }
        );
    }
}

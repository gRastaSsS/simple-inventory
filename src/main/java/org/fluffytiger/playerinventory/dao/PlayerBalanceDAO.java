package org.fluffytiger.playerinventory.dao;

import org.fluffytiger.playerinventory.model.Coins;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerBalanceDAO {
    private final JdbcTemplate jdbcTemplate;

    public PlayerBalanceDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(String playerId, Coins amount) {
        jdbcTemplate.update(
            "INSERT INTO PlayerBalance VALUES (?, ?, ?)",
            ps -> {
                ps.setString(1, playerId);
                ps.setInt(2, amount.getGold());
                ps.setInt(3, amount.getSilver());
            }
        );
    }

    public boolean update(String playerId, Coins amount) {
        int result = jdbcTemplate.update(
            "UPDATE PlayerBalance SET amountGold = ?, amountSilver = ? WHERE playerId = ?",
            ps -> {
                ps.setInt(1, amount.getGold());
                ps.setInt(2, amount.getSilver());
                ps.setString(3, playerId);
            }
        );

        return result == 1;
    }

    public Coins get(String playerId, boolean acquireLock) {
        String sql = "SELECT amountGold, amountSilver FROM PlayerBalance WHERE playerId = ?";
        if (acquireLock) {
            sql += " FOR UPDATE";
        }

        return jdbcTemplate.query(
            sql,
            ps -> ps.setString(1, playerId),
            rs -> {
                rs.next();
                return new Coins(
                    rs.getInt(1),
                    rs.getInt(2)
                );
            }
        );
    }
}

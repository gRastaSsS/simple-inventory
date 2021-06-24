package org.fluffytiger.playerinventory.dao;

import org.fluffytiger.playerinventory.model.Player;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerDAO {
    private final JdbcTemplate jdbcTemplate;

    public PlayerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(Player player) {
        jdbcTemplate.update(
            "INSERT INTO Player VALUES (?)",
            ps -> ps.setString(1, player.getId())
        );
    }
}

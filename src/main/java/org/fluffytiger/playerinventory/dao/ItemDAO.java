package org.fluffytiger.playerinventory.dao;

import org.fluffytiger.playerinventory.model.Coins;
import org.fluffytiger.playerinventory.model.Item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDAO {
    private final JdbcTemplate jdbcTemplate;

    public ItemDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(Item item) {
        jdbcTemplate.update(
            "INSERT INTO Item VALUES (?, ?, ?)",
            ps -> {
                ps.setString(1, item.getId());
                ps.setInt(2, item.getPrice().getGold());
                ps.setInt(3, item.getPrice().getSilver());
            }
        );
    }

    public Item get(String id) {
        return jdbcTemplate.query(
            "SELECT id, priceGold, priceSilver FROM Item WHERE id = ?",
            ps -> ps.setString(1, id),
            rs -> {
                rs.next();
                return new Item(
                    rs.getString(1),
                    new Coins(
                        rs.getInt(2),
                        rs.getInt(3)
                    )
                );
            }
        );
    }
}

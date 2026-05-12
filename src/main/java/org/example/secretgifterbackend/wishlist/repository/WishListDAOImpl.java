package org.example.secretgifterbackend.wishlist.repository;

import org.example.secretgifterbackend.wishlist.api.response.WishListItemResponse;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class WishListDAOImpl implements WishListDAO {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public WishListDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer create(Integer participantId, String itemText) {
        String sql = """
                INSERT INTO wishlist_item (participant_id, item_text)
                VALUES (:participantId, :itemText)
                RETURNING id
                """;
        return jdbcTemplate.queryForObject(sql, Map.of("participantId", participantId,  "itemText", itemText),
                Integer.class);
    }
    @Override
    public List<WishListItemResponse> findByParticipantId(
            Integer participantId
    ) {

        String sql = """
                SELECT
                    id,
                    item_text
                FROM wishlist_item
                WHERE participant_id = :participantId
                """;

        return jdbcTemplate.query(
                sql,
                Map.of("participantId", participantId),
                (rs, rowNum) -> new WishListItemResponse(
                        rs.getInt("id"),
                        rs.getString("item_text")
                )
        );
    }
}

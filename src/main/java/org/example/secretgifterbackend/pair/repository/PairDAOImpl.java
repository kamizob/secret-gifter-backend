package org.example.secretgifterbackend.pair.repository;

import org.example.secretgifterbackend.pair.domain.PairDTO;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PairDAOImpl implements PairDAO {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PairDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void deleteByRoomId(Integer roomId) {
        String sql = "DELETE FROM pair WHERE room_id = :roomId";
        jdbcTemplate.update(sql, Map.of("roomId", roomId));
    }
    @Override
    public void saveAll(List<PairDTO> pairs) {
        String sql = """
                INSERT INTO pair (giver, receiver, room_id)
                VALUES (:giver, :receiver, :roomId)
                """;
        for (PairDTO pair : pairs) {
            jdbcTemplate.update(sql, Map.of(
                    "giver", pair.giver(),
                    "receiver", pair.receiver(),
                    "roomId", pair.roomId()
            ));
        }
    }

    @Override
    public List<PairDTO> findByRoomId(Integer roomId) {
        String sql = """
                SELECT giver, receiver, room_id
                FROM pair WHERE room_id = :roomId
                """;
        return jdbcTemplate.query(sql, Map.of("roomId", roomId),
                (rs, rowNum) -> new PairDTO(
                        rs.getString("giver"),
                        rs.getString("receiver"),
                        rs.getInt("room_id")
                ));
    }
    @Override
    public PairDTO findByGiver(String giver, Integer roomId) {
        String sql = """
                SELECT giver, receiver, room_id
                FROM pair
                WHERE giver = :giver AND room_id = :roomId
        """;
        return jdbcTemplate.queryForObject(sql,
                Map.of("giver", giver, "roomId", roomId),
                (rs, rowNum) -> new PairDTO(
                        rs.getString("giver"),
                        rs.getString("receiver"),
                        rs.getInt("room_id")
                ));
    }
}

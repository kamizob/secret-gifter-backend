package org.example.secretgifterbackend.room.repository;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository
public class RoomDAOImpl implements RoomDAO {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public RoomDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Integer createRoom(String code) {
        String sql = """
                INSERT INTO room (code)
                VALUES (:code)
                RETURNING id
                """;
        return jdbcTemplate.queryForObject(sql, Map.of("code", code) , Integer.class);
    }
}

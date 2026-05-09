package org.example.secretgifterbackend.room.repository;

import org.example.secretgifterbackend.room.api.response.CreateRoomResponse;
import org.example.secretgifterbackend.room.domain.RoomStatus;
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
    public CreateRoomResponse createRoom(
            String code,
            RoomStatus status
    ) {

        String sql = """
            INSERT INTO room (code, status)
            VALUES (:code, :status)
            RETURNING id
            """;

        Integer id = jdbcTemplate.queryForObject(
                sql,
                Map.of(
                        "code", code,
                        "status", status.name()
                ),
                Integer.class
        );

        return new CreateRoomResponse(id, code);
    }
    @Override
    public Integer findRoomIdByCode(String code) {

        String sql = """
            SELECT id
            FROM room
            WHERE code = :code
            """;

        return jdbcTemplate.queryForObject(
                sql,
                Map.of("code", code),
                Integer.class
        );
    }
}

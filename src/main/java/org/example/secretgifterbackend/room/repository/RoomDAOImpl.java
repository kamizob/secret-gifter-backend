package org.example.secretgifterbackend.room.repository;

import org.example.secretgifterbackend.room.api.response.CreateRoomResponse;
import org.example.secretgifterbackend.room.api.response.RoomHistoryResponse;
import org.example.secretgifterbackend.room.domain.RoomStatus;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
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
        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    Map.of("code", code),
                    Integer.class
            );
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Room not found");
        }
    }
    @Override
    public List<RoomHistoryResponse> getHistory() {

        String sql = """
        SELECT
            r.id,
            r.code,
            r.status,
            r.created_at,
            COUNT(p.id) AS participant_count
        FROM room r
        LEFT JOIN participant p
            ON r.id = p.room_id
        GROUP BY r.id
        ORDER BY r.created_at DESC
        """;

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new RoomHistoryResponse(
                        rs.getInt("id"),
                        rs.getString("code"),
                        rs.getString("status"),
                        rs.getInt("participant_count"),
                        rs.getTimestamp("created_at")
                                .toLocalDateTime()
                )
        );
    }
}

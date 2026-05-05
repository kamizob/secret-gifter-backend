package org.example.secretgifterbackend.participant.repository;

import org.example.secretgifterbackend.participant.api.request.CreateParticipantRequest;
import org.example.secretgifterbackend.participant.api.response.ParticipantResponse;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ParticipantDAOImpl implements ParticipantDAO {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ParticipantDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer createParticipant(String name, Integer roomId) {
        String sql = """
                INSERT INTO participant (name, room_id)
                VALUES (:name, :roomId)
                RETURNING id
                """;
        return jdbcTemplate.queryForObject(sql, Map.of("name", name, "roomId", roomId), Integer.class);

    }
    @Override
    public List<ParticipantResponse> findByRoomId(Integer roomId) {
        String sql = """
                SELECT id, name, room_id
                FROM participant
                WHERE room_id = :roomId
                """;

        return jdbcTemplate.query(sql,
                Map.of("roomId", roomId),
                (rs, rowNum) ->  new ParticipantResponse(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("room_id")
                ));
    }
}

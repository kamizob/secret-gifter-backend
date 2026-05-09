package org.example.secretgifterbackend.participant.repository;

import org.example.secretgifterbackend.participant.api.request.CreateParticipantRequest;
import org.example.secretgifterbackend.participant.api.response.ParticipantResponse;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class ParticipantDAOImpl implements ParticipantDAO {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ParticipantDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ParticipantResponse createParticipant(String name, Integer roomId) {

        UUID publicId = UUID.randomUUID();

        String sql = """
            INSERT INTO participant (public_id, name, room_id)
            VALUES (:publicId, :name, :roomId)
            RETURNING id
            """;

        Integer id = jdbcTemplate.queryForObject(
                sql,
                Map.of(
                        "publicId", publicId,
                        "name", name,
                        "roomId", roomId
                ),
                Integer.class
        );

        return new ParticipantResponse(
                id,
                publicId,
                name,
                roomId
        );
    }
    @Override
    public List<ParticipantResponse> findByRoomId(Integer roomId) {
        String sql = """
                SELECT id, public_id, name, room_id
                FROM participant
                WHERE room_id = :roomId
                """;

        return jdbcTemplate.query(sql,
                Map.of("roomId", roomId),
                (rs, rowNum) -> new ParticipantResponse(
                        rs.getInt("id"),
                        UUID.fromString(rs.getString("public_id")),
                        rs.getString("name"),
                        rs.getInt("room_id")
                ));
    }
    @Override
    public ParticipantResponse findByPublicId(UUID publicId) {

        String sql = """
            SELECT
                id,
                public_id,
                name,
                room_id
            FROM participant
            WHERE public_id = :publicId
            """;

        return jdbcTemplate.queryForObject(
                sql,
                Map.of("publicId", publicId),
                (rs, rowNum) -> new ParticipantResponse(
                        rs.getInt("id"),
                        UUID.fromString(rs.getString("public_id")),
                        rs.getString("name"),
                        rs.getInt("room_id")
                )
        );
    }
    @Override
    public ParticipantResponse findById(Integer id) {

        String sql = """
            SELECT
                id,
                public_id,
                name,
                room_id
            FROM participant
            WHERE public_id = :publicId
            """;

        return jdbcTemplate.queryForObject(
                sql,
                Map.of("id", id),
                (rs, rowNum) -> new ParticipantResponse(
                        rs.getInt("id"),
                        UUID.fromString(rs.getString("public_id")),
                        rs.getString("name"),
                        rs.getInt("room_id")
                )
        );
    }

}

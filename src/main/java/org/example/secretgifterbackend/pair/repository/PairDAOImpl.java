package org.example.secretgifterbackend.pair.repository;

import org.example.secretgifterbackend.pair.api.response.PairResponse;
import org.example.secretgifterbackend.pair.domain.PairDTO;
import org.example.secretgifterbackend.participant.api.response.ParticipantResponse;
import org.example.secretgifterbackend.wishlist.repository.WishListDAO;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class PairDAOImpl implements PairDAO {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final WishListDAO wishListDAO;

    public PairDAOImpl(NamedParameterJdbcTemplate jdbcTemplate,
                       WishListDAO wishListDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.wishListDAO = wishListDAO;
    }

    @Override
    public void deleteByRoomId(Integer roomId) {
        String sql = "DELETE FROM pair WHERE room_id = :roomId";
        jdbcTemplate.update(sql, Map.of("roomId", roomId));
    }
    @Override
    public void saveAll(List<PairDTO> pairs) {
        String sql = """
                INSERT INTO pair (
                    giver_participant_id,
                    receiver_participant_id,
                    room_id
                )
                VALUES (
                    :giverParticipantId,
                    :receiverParticipantId,
                    :roomId
                )
                """;
        for (PairDTO pair : pairs) {
            jdbcTemplate.update(sql, Map.of(
                    "giverParticipantId", pair.giverParticipantId(),
                    "receiverParticipantId", pair.receiverParticipantId(),
                    "roomId", pair.roomId()
            ));
        }
    }

    @Override
    public List<PairDTO> findByRoomId(Integer roomId) {

        String sql = """
            SELECT
                giver_participant_id,
                receiver_participant_id,
                room_id
            FROM pair
            WHERE room_id = :roomId
            """;

        return jdbcTemplate.query(
                sql,
                Map.of("roomId", roomId),
                (rs, rowNum) -> new PairDTO(
                        rs.getInt("giver_participant_id"),
                        rs.getInt("receiver_participant_id"),
                        rs.getInt("room_id")
                )
        );
    }
    @Override
    public PairDTO findByGiverParticipantId(
            Integer giverParticipantId,
            Integer roomId
    ) {

        String sql = """
            SELECT
                giver_participant_id,
                receiver_participant_id,
                room_id
            FROM pair
            WHERE giver_participant_id = :giverParticipantId
              AND room_id = :roomId
            """;

        return jdbcTemplate.queryForObject(
                sql,
                Map.of(
                        "giverParticipantId", giverParticipantId,
                        "roomId", roomId
                ),
                (rs, rowNum) -> new PairDTO(
                        rs.getInt("giver_participant_id"),
                        rs.getInt("receiver_participant_id"),
                        rs.getInt("room_id")
                )
        );
    }
    @Override
    public List<PairResponse> findPairResponsesByRoomId(Integer roomId) {

        String sql = """
            
            SELECT
                giver.name AS giver_name,
                receiver.name AS receiver_name,
                receiver.id AS receiver_id
            FROM pair p
            JOIN participant giver
                ON p.giver_participant_id = giver.id
            JOIN participant receiver
                ON p.receiver_participant_id = receiver.id
            WHERE p.room_id = :roomId
            """;

        return jdbcTemplate.query(
                sql,
                Map.of("roomId", roomId),
                (rs, rowNum) -> new PairResponse(
                        rs.getString("giver_name"),
                        rs.getString("receiver_name"),
                        wishListDAO.findByParticipantId(
                                rs.getInt("receiver_id")
                        )
                )
        );
    }

}

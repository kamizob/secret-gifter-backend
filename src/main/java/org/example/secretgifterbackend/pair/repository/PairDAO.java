package org.example.secretgifterbackend.pair.repository;

import org.example.secretgifterbackend.pair.api.response.PairResponse;
import org.example.secretgifterbackend.pair.domain.PairDTO;
import org.example.secretgifterbackend.participant.api.response.ParticipantResponse;

import java.util.List;
import java.util.UUID;

public interface PairDAO {
    void deleteByRoomId(Integer roomId);
    void saveAll(List<PairDTO> pairs);
    List<PairDTO> findByRoomId(Integer roomId);
    PairDTO findByGiverParticipantId(Integer giverParticipantId, Integer roomId);
    List<PairResponse> findPairResponsesByRoomId(Integer roomId);
}

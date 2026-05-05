package org.example.secretgifterbackend.pair.repository;

import org.example.secretgifterbackend.pair.domain.PairDTO;

import java.util.List;

public interface PairDAO {
    void deleteByRoomId(Integer roomId);
    void saveAll(List<PairDTO> pairs);
    List<PairDTO> findByRoomId(Integer roomId);
    PairDTO findByGiver(String giver, Integer roomId);
}

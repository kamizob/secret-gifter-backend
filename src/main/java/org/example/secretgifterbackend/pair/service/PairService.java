package org.example.secretgifterbackend.pair.service;

import org.example.secretgifterbackend.pair.api.response.PairResponse;
import org.example.secretgifterbackend.pair.api.response.RevealPairResponse;
import org.example.secretgifterbackend.pair.domain.PairDTO;
import org.example.secretgifterbackend.pair.repository.PairDAO;
import org.example.secretgifterbackend.participant.repository.ParticipantDAO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PairService {
    private final ParticipantDAO participantDAO;
    private final PairDAO pairDAO;

    public PairService(ParticipantDAO participantDAO, PairDAO pairDAO) {
        this.participantDAO = participantDAO;
        this.pairDAO = pairDAO;
    }
    public List<PairResponse> generatePairs(Integer roomId) {
        var participants = participantDAO.findByRoomId(roomId);
        if(participants.size() < 2) {
            throw new RuntimeException("Need at least 2 participants");
        }
        List<String> names = participants.stream()
                .map(p -> p.name())
                .toList();

        List<String> shuffled = new ArrayList<>(names);
        Collections.shuffle(shuffled);

        List<PairDTO> pairsToSave = new ArrayList<>();
        List<PairResponse> response =  new ArrayList<>();

        for (int i = 0; i < shuffled.size(); i++) {
            String giver = shuffled.get(i);
            String receiver = shuffled.get((i+1) % shuffled.size());
            pairsToSave.add(new PairDTO(giver, receiver, roomId));
            response.add(new PairResponse(giver, receiver));
        }
        pairDAO.deleteByRoomId(roomId);
        pairDAO.saveAll(pairsToSave);
        return response;
    }
    public List<PairResponse> getPairs(Integer roomId) {
        return pairDAO.findByRoomId(roomId)
                .stream()
                .map(p -> new PairResponse(p.giver(), p.receiver()))
                .toList();
    }
    public RevealPairResponse reveal(String name, Integer roomId) {
        PairDTO pair = pairDAO.findByGiver(name, roomId);
        if(pair == null) {
            throw new RuntimeException("Pair not found");
        }

        return new RevealPairResponse(pair.giver(), pair.receiver());
    }
}

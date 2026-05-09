package org.example.secretgifterbackend.pair.service;

import org.example.secretgifterbackend.pair.api.response.PairResponse;
import org.example.secretgifterbackend.pair.api.response.RevealPairResponse;
import org.example.secretgifterbackend.pair.domain.PairDTO;
import org.example.secretgifterbackend.pair.repository.PairDAO;
import org.example.secretgifterbackend.participant.api.response.ParticipantResponse;
import org.example.secretgifterbackend.participant.repository.ParticipantDAO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

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
        List<ParticipantResponse> shuffled = new ArrayList<>(participants);
        Collections.shuffle(shuffled);

        List<PairDTO> pairsToSave = new ArrayList<>();
        List<PairResponse> response =  new ArrayList<>();

        for (int i = 0; i < shuffled.size(); i++) {

            var giver = shuffled.get(i);
            var receiver = shuffled.get((i + 1) % shuffled.size());

            pairsToSave.add(
                    new PairDTO(
                            giver.id(),
                            receiver.id(),
                            roomId
                    )
            );

            response.add(
                    new PairResponse(
                            giver.name(),
                            receiver.name()
                    )
            );
        }
        pairDAO.deleteByRoomId(roomId);
        pairDAO.saveAll(pairsToSave);
        return response;
    }
    public List<PairResponse> getPairs(Integer roomId) {
        return pairDAO.findPairResponsesByRoomId(roomId);
    }
    public RevealPairResponse reveal(UUID publicId, Integer roomId) {
        var participant = participantDAO.findByPublicId(publicId);
        PairDTO pair = pairDAO.findByGiverParticipantId(
                participant.id(),
                roomId
        );

        if(pair == null) {
            throw new RuntimeException("Pair not found");
        }
        var receiver = participantDAO.findById(
                pair.receiverParticipantId()
        );

        return new RevealPairResponse(
                participant.name(),
                receiver.name()
        );
    }
}

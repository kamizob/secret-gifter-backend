package org.example.secretgifterbackend.participant.service;

import org.example.secretgifterbackend.participant.api.request.CreateParticipantRequest;
import org.example.secretgifterbackend.participant.api.response.ParticipantResponse;
import org.example.secretgifterbackend.participant.repository.ParticipantDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantService {
    private final ParticipantDAO participantDAO;

    public ParticipantService(ParticipantDAO participantDAO) {
        this.participantDAO = participantDAO;
    }
    public ParticipantResponse createParticipant(CreateParticipantRequest request) {
        Integer id = participantDAO.createParticipant(request.name(), request.roomId());
        return new ParticipantResponse(id, request.name(), request.roomId());
    }
    public List<ParticipantResponse> getByRoomId(Integer roomId) {
        return participantDAO.findByRoomId(roomId);
    }
}

package org.example.secretgifterbackend.participant.repository;

import org.example.secretgifterbackend.participant.api.response.ParticipantResponse;

import java.util.List;
import java.util.UUID;

public interface ParticipantDAO {
    ParticipantResponse createParticipant(String name, Integer roomId);
    List<ParticipantResponse> findByRoomId(Integer roomId);
    ParticipantResponse findByPublicId(UUID publicId);
    ParticipantResponse findById(Integer id);
}

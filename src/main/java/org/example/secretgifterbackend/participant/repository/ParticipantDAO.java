package org.example.secretgifterbackend.participant.repository;

import org.example.secretgifterbackend.participant.api.response.ParticipantResponse;

import java.util.List;

public interface ParticipantDAO {
    Integer createParticipant(String name, Integer roomId);
    List<ParticipantResponse> findByRoomId(Integer roomId);
}

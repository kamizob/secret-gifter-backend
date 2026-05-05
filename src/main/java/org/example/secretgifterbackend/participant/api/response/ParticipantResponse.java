package org.example.secretgifterbackend.participant.api.response;

public record ParticipantResponse(
        Integer id,
        String name,
        Integer roomId
) {
}

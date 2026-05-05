package org.example.secretgifterbackend.participant.api.request;

public record CreateParticipantRequest(
        String name,
        Integer roomId
) {
}

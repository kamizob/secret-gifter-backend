package org.example.secretgifterbackend.participant.api.response;

import java.util.UUID;

public record ParticipantResponse(
        Integer id,
        UUID publicId,
        String name,
        Integer roomId
) {
}

package org.example.secretgifterbackend.pair.domain;

public record PairDTO(
        Integer giverParticipantId,
        Integer receiverParticipantId,
        Integer roomId
) {
}

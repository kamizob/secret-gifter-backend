package org.example.secretgifterbackend.pair.domain;

public record PairDTO(
        String giver,
        String receiver,
        Integer roomId
) {
}

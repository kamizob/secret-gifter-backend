package org.example.secretgifterbackend.pair.api.request;

public record RevealPairRequest(
        String name,
        Integer roomId
) {
}

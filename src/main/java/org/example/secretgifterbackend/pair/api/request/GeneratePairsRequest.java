package org.example.secretgifterbackend.pair.api.request;

import jakarta.validation.constraints.NotNull;

public record GeneratePairsRequest(
        @NotNull Integer roomId
) {
}

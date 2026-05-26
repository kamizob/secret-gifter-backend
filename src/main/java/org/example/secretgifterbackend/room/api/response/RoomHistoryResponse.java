package org.example.secretgifterbackend.room.api.response;

import java.time.LocalDateTime;

public record RoomHistoryResponse(
        Integer roomId,
        String code,
        String status,
        Integer participantCount,
        LocalDateTime createdAt
) {
}

package org.example.secretgifterbackend.room.api.request;
import jakarta.validation.constraints.NotBlank;

public record CreateRoomRequest(
        @NotBlank String code

) {
}

package org.example.secretgifterbackend.participant.api.response;

import org.example.secretgifterbackend.wishlist.api.response.WishListItemResponse;

import java.util.List;
import java.util.UUID;

public record ParticipantResponse(
        Integer id,
        UUID publicId,
        String name,
        Integer roomId,
        List<WishListItemResponse> wishList
) {
}

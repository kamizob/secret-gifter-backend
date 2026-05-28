package org.example.secretgifterbackend.pair.api.response;

import org.example.secretgifterbackend.wishlist.api.response.WishListItemResponse;

import java.util.List;

public record PairResponse(
        String giverName,
        String receiverName,
        List<WishListItemResponse> wishList
) {
}

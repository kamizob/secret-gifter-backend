package org.example.secretgifterbackend.wishlist.repository;

import org.example.secretgifterbackend.wishlist.api.response.WishListItemResponse;

import java.util.List;

public interface WishListDAO {
    Integer create(Integer participantId, String itemText);
    List<WishListItemResponse> findByParticipantId(Integer participantId);
}

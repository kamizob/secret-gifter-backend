package org.example.secretgifterbackend.wishlist.service;

import org.example.secretgifterbackend.participant.repository.ParticipantDAO;
import org.example.secretgifterbackend.wishlist.api.request.CreateWishListItemRequest;
import org.example.secretgifterbackend.wishlist.api.response.WishListItemResponse;
import org.example.secretgifterbackend.wishlist.repository.WishListDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WishListService {
    private final WishListDAO wishListDAO;
    private final ParticipantDAO participantDAO;

    public WishListService(WishListDAO wishListDAO, ParticipantDAO participantDAO) {
        this.wishListDAO = wishListDAO;
        this.participantDAO = participantDAO;
    }
    public WishListItemResponse create(CreateWishListItemRequest request) {
        var participant = participantDAO.findByPublicId(request.participantPublicId());

        Integer id = wishListDAO.create(participant.id(),  request.itemText());
        return new WishListItemResponse(id, request.itemText());

    }

    public List<WishListItemResponse> getByParticipantPublicId(UUID publicId) {
        var participant = participantDAO.findByPublicId(publicId);

        return wishListDAO.findByParticipantId(participant.id());

    }
}

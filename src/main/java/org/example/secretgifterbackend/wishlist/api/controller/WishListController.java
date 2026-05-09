package org.example.secretgifterbackend.wishlist.api.controller;

import jakarta.validation.Valid;
import org.example.secretgifterbackend.wishlist.api.request.CreateWishListItemRequest;
import org.example.secretgifterbackend.wishlist.api.response.WishListItemResponse;
import org.example.secretgifterbackend.wishlist.service.WishListService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wishlist")
public class WishListController {
    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @PostMapping
    public WishListItemResponse create (@Valid @RequestBody CreateWishListItemRequest request) {
        return wishListService.create(request);
    }
}

package org.example.secretgifterbackend.room.api.controller;

import org.example.secretgifterbackend.room.api.response.CreateRoomResponse;
import org.example.secretgifterbackend.room.service.RoomService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public CreateRoomResponse createRoom() {
        return roomService.createRoom();
    }
}

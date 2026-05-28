package org.example.secretgifterbackend.room.api.controller;

import org.example.secretgifterbackend.room.api.request.CreateRoomRequest;
import org.example.secretgifterbackend.room.api.response.CreateRoomResponse;
import org.example.secretgifterbackend.room.api.response.JoinRoomResponse;
import org.example.secretgifterbackend.room.api.response.RoomHistoryResponse;
import org.example.secretgifterbackend.room.service.RoomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    @PostMapping("/join")
    public JoinRoomResponse joinRoom(@RequestParam String code) {
        return roomService.joinRoom(code);
    }
    @GetMapping("/history")
    public List<RoomHistoryResponse> getHistory() {
        return roomService.getHistory();
    }
    @PostMapping("/{roomId}/start")
    public void startRoom(@PathVariable Integer roomId) {
        roomService.startRoom(roomId);
    }
    @GetMapping("/{roomId}/status")
    public String getStatus(@PathVariable Integer roomId) {
        return roomService
                .getStatus(roomId)
                .name();
    }
    @PostMapping("/{roomId}/finish")
    public void finishRoom(@PathVariable Integer roomId) {
        roomService.finishRoom(roomId);
    }
}

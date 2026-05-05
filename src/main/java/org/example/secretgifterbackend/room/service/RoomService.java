package org.example.secretgifterbackend.room.service;

import org.example.secretgifterbackend.room.api.request.CreateRoomRequest;
import org.example.secretgifterbackend.room.api.response.CreateRoomResponse;
import org.example.secretgifterbackend.room.repository.RoomDAO;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    private final RoomDAO roomDAO;

    public RoomService(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    public CreateRoomResponse createRoom(CreateRoomRequest createRoomRequest) {
        Integer id = roomDAO.createRoom(createRoomRequest.code());
        return new CreateRoomResponse(id, createRoomRequest.code());

    }
}

package org.example.secretgifterbackend.room.service;

import org.example.secretgifterbackend.room.api.request.CreateRoomRequest;
import org.example.secretgifterbackend.room.api.response.CreateRoomResponse;
import org.example.secretgifterbackend.room.domain.RoomStatus;
import org.example.secretgifterbackend.room.repository.RoomDAO;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    private final RoomDAO roomDAO;

    public RoomService(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    public CreateRoomResponse createRoom() {
        String code = generateRoomCode();
        return roomDAO.createRoom(code, RoomStatus.WAITING);

    }

    private String generateRoomCode() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int index = (int) (Math.random() * chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }
}

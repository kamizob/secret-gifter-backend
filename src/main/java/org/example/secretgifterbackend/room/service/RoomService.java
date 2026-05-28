package org.example.secretgifterbackend.room.service;

import org.example.secretgifterbackend.room.api.request.CreateRoomRequest;
import org.example.secretgifterbackend.room.api.response.CreateRoomResponse;
import org.example.secretgifterbackend.room.api.response.JoinRoomResponse;
import org.example.secretgifterbackend.room.api.response.RoomHistoryResponse;
import org.example.secretgifterbackend.room.domain.RoomStatus;
import org.example.secretgifterbackend.room.repository.RoomDAO;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public JoinRoomResponse joinRoom(String code) {

        Integer roomId = roomDAO.findRoomIdByCode(
                code.toUpperCase()
        );

        return new JoinRoomResponse(roomId);
    }
    public List<RoomHistoryResponse> getHistory() {
        return roomDAO.getHistory();
    }
    public void startRoom(Integer roomId) {

        roomDAO.updateStatus(
                roomId,
                RoomStatus.STARTED
        );
    }
    public RoomStatus getStatus(Integer roomId) {
        return roomDAO.getStatus(roomId);
    }
    public void finishRoom(Integer roomId) {

        roomDAO.updateStatus(
                roomId,
                RoomStatus.FINISHED
        );
    }
}

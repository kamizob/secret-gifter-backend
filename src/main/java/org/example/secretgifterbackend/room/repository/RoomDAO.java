package org.example.secretgifterbackend.room.repository;

import org.example.secretgifterbackend.room.api.response.CreateRoomResponse;
import org.example.secretgifterbackend.room.api.response.RoomHistoryResponse;
import org.example.secretgifterbackend.room.domain.RoomStatus;

import java.util.List;

public interface RoomDAO {
    CreateRoomResponse createRoom(String code, RoomStatus status);
    Integer findRoomIdByCode(String code);
    List<RoomHistoryResponse> getHistory();

}

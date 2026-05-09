package org.example.secretgifterbackend.room.repository;

import org.example.secretgifterbackend.room.api.response.CreateRoomResponse;
import org.example.secretgifterbackend.room.domain.RoomStatus;

public interface RoomDAO {
    CreateRoomResponse createRoom(String code, RoomStatus status);
    Integer findRoomIdByCode(String code);

}

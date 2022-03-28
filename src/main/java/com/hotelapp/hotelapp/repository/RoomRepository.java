package com.hotelapp.hotelapp.repository;

import com.hotelapp.hotelapp.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("select room from Room room where lower(room.roomName) = lower(:name)")
    Room getRoomByName(@Param("name") String name);

    @Modifying
    @Query("DELETE from Room where lower(roomName) = lower(:roomName)")
    void removeRoom(@Param("roomName") String roomName);

    @Modifying
    @Query("update Room set roomName = :roomName where lower(roomName) = lower(:newRoomName)")
    void updateRoomName(@Param("roomName") String roomName,
                        @Param("newRoomName") String newRoomName);
}

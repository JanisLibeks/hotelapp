package com.hotelapp.hotelapp.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.hotelapp.hotelapp.model.Room;
import com.hotelapp.hotelapp.service.RoomService;

import java.util.Date;
import java.util.List;

public class Query implements GraphQLQueryResolver {
    private final RoomService roomService;

    public Query(RoomService roomService) {
        this.roomService = roomService;
    }

    public Room getRoom(String roomName) {
        return roomService.viewRoom(roomName);
    }

    public long getFreeRoomCount(Date from, Date to) {
        return (int) roomService.getFreeRoomCount(from, to);
    }

    public long getBookedRoomCount(Date from, Date to) {
        return (int) roomService.getBookedRoomCount(from, to);
    }

    public List<Room> getFreeRooms(Date from, Date to) {
        return roomService.getFreeRooms(from, to);
    }
}

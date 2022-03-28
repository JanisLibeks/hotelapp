package com.hotelapp.hotelapp.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.hotelapp.hotelapp.model.Reservation;
import com.hotelapp.hotelapp.model.Room;
import com.hotelapp.hotelapp.service.RoomService;

public class Mutation implements GraphQLMutationResolver {

    private final RoomService roomService;

    public Mutation(RoomService roomService) {
        this.roomService = roomService;
    }

    public Room addRoom(String name) {
        Room room = new Room();
        room.setRoomName(name);
        return roomService.addRoom(room);
    }

    public void removeRoom(String roomName) {
        roomService.removeRoom(roomName);
    }

    public void changeRoomName(String oldRoomName, String newRoomName) {
        roomService.changeRoomName(oldRoomName, newRoomName);
    }

    public Room addRoomReservation(String roomName, Reservation reservation) {
        return roomService.addRoomReservation(roomName, reservation);
    }

    public Room removeRoomReservation(String roomName, int reservationId) {
        return roomService.removeRoomReservation(roomName, reservationId);
    }
}

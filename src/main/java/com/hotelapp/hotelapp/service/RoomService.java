package com.hotelapp.hotelapp.service;

import com.hotelapp.hotelapp.model.Reservation;
import com.hotelapp.hotelapp.model.Room;
import com.hotelapp.hotelapp.repository.RoomRepository;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room viewRoom(String roomName) {
        return roomRepository.getRoomByName(roomName);
    }

    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    public void removeRoom(String roomName) {
        roomRepository.removeRoom(roomName);
    }

    public void changeRoomName(String roomName, String newRoomName) {
        roomRepository.updateRoomName(roomName, newRoomName);
    }

    public Room addRoomReservation(String roomName, Reservation reservation) {
        Room room = roomRepository.getRoomByName(roomName);
        if (isRoomFree(room, reservation)) {
            Set<Reservation> reservations = room.getReservations();
            reservations.add(reservation);
            room.setReservations(reservations);
        }
        return roomRepository.save(room);
    }

    public Room removeRoomReservation(String roomName, int reservationId) {
        Room room = roomRepository.getRoomByName(roomName);
        Set<Reservation> reservations = room.getReservations();
        reservations.removeIf(reservation -> reservation.getReservationId().equals((long) reservationId));
        if (room.getReservations().size() <= reservations.size()) {
            room.setMessage("No rooms removed");
        }
        return roomRepository.save(room);
    }

    public List<Room> getFreeRooms(Date from, Date to) {
        List<Room> rooms = roomRepository.findAll();
        Reservation statisticsRange = new Reservation();
        statisticsRange.setDateFrom(from);
        statisticsRange.setDateTo(to);
        return rooms.stream().filter(room -> isRoomFree(room, statisticsRange)).collect(Collectors.toList());
    }

    public long getFreeRoomCount(Date from, Date to) {
        List<Room> rooms = roomRepository.findAll();
        Reservation statisticsRange = new Reservation();
        statisticsRange.setDateFrom(from);
        statisticsRange.setDateTo(to);
        return rooms.stream().filter(room -> isRoomFree(room, statisticsRange)).count();
    }

    public long getBookedRoomCount(Date from, Date to) {
        List<Room> rooms = roomRepository.findAll();
        Reservation statisticsRange = new Reservation();
        statisticsRange.setDateFrom(from);
        statisticsRange.setDateTo(to);
        return rooms.stream().filter(room -> !isRoomFree(room, statisticsRange)).count();
    }

    public boolean isRoomFree(Room room, Reservation reservation) {
        return room.getReservations().stream()
                .noneMatch(existingReservation -> hasOverlap(existingReservation, reservation));
    }

    private boolean hasOverlap(Reservation existingReservation, Reservation newReservation) {
        DateTime existingFrom = new DateTime(existingReservation.getDateFrom());
        DateTime existingTo = new DateTime(existingReservation.getDateTo());
        DateTime newFrom = new DateTime(newReservation.getDateFrom());
        DateTime newTo = new DateTime(newReservation.getDateTo());
        Interval existingInterval = new Interval(existingFrom, existingTo);
        Interval newInterval = new Interval(newFrom, newTo);
        return existingInterval.overlaps(newInterval);
    }
}

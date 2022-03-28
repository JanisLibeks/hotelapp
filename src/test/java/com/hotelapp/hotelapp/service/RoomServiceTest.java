package com.hotelapp.hotelapp.service;

import com.hotelapp.hotelapp.model.Reservation;
import com.hotelapp.hotelapp.model.Room;
import com.hotelapp.hotelapp.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class RoomServiceTest {

    private final RoomRepository roomRepository = mock(RoomRepository.class);
    private final RoomService roomService = new RoomService(roomRepository);

    @Test
    public void viewRoom_returnRoom_nameExists() {
        doReturn(new Room()).when(roomRepository).getRoomByName(anyString());

        assertNotNull(roomService.viewRoom("1.Room"));
    }

    @Test
    public void addRoom_returnRoom() {
        doReturn(new Room()).when(roomRepository).save(any(Room.class));

        assertNotNull(roomService.addRoom(new Room()));
    }

    @Test
    public void getFreeRooms_returnsRooms() {
        when(roomRepository.findAll()).thenReturn(getRooms());

        assertNotNull(roomService.getFreeRooms(new Date(), new Date()));
    }

    @Test
    public void getFreeRoomCount_returnsOne() {
        when(roomRepository.findAll()).thenReturn(getRooms());

        assertEquals(1, roomService.getFreeRoomCount(new Date(), new Date()));
    }

    @Test
    public void getBookedRoomCount_returnsZero() {
        when(roomRepository.findAll()).thenReturn(getRooms());

        assertEquals(0, roomService.getBookedRoomCount(new Date(), new Date()));
    }

    @Test
    public void isRoomFree_returnsTrue() {
        assertTrue(roomService.isRoomFree(getRooms().get(0), getReservations().iterator().next()));
    }

    private List<Room> getRooms() {
        Room room = new Room();
        room.setRoomName("anyName");
        room.setReservations(getReservations());
        return new ArrayList<>(List.of(room));
    }

    private HashSet<Reservation> getReservations() {
        Reservation reservation = new Reservation();
        reservation.setDateFrom(new Date());
        reservation.setDateTo(new Date());
        return new HashSet<>(List.of(reservation));
    }
}
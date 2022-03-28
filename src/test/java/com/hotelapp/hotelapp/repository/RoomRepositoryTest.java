package com.hotelapp.hotelapp.repository;

import com.hotelapp.hotelapp.model.Room;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
class RoomRepositoryTest {

    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void whenCalledSave_thenCorrectNumberOfRooms() {
        roomRepository.save(new Room(1L, "room1", new HashSet<>()));
        List<Room> rooms = roomRepository.findAll();

        assertEquals(10, rooms.size());
    }

}
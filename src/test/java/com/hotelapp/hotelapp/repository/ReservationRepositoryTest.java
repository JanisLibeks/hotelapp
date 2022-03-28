package com.hotelapp.hotelapp.repository;

import com.hotelapp.hotelapp.model.Reservation;
import com.hotelapp.hotelapp.model.Room;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void whenCalledSave_thenCorrectNumberOfReservation() {
        reservationRepository.save(new Reservation(1L, new Date(), new Date(), new Room(1L, "", new HashSet<>())));
        List<Reservation> reservations = reservationRepository.findAll();

        assertEquals(30, reservations.size());
    }

}

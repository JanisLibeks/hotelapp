package com.hotelapp.hotelapp.model;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    Long roomId;
    @Column(name = "name", unique = true)
    String roomName;
    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    Set<Reservation> reservations;
    String message;

    public Room(Long roomId, String roomName, Set<Reservation> reservations) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.reservations = reservations;
    }

    public Room() {
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long id) {
        this.roomId = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

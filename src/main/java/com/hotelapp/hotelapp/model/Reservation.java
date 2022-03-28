package com.hotelapp.hotelapp.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    Long reservationId;
    @Column(name = "reservation_from")
    Date dateFrom;
    @Column(name = "reservation_to")
    Date dateTo;
    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "room_id", nullable = false)
    Room room;

    public Reservation(Long reservationId, Date dateFrom, Date dateTo, Room room) {
        this.reservationId = reservationId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.room = room;
    }

    public Reservation() {
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long id) {
        this.reservationId = id;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date date) {
        this.dateFrom = date;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}

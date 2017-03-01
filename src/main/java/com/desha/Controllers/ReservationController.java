package com.desha.Controllers;

import com.desha.Beans.Guest;
import com.desha.Beans.Invite;
import com.desha.Beans.Reservation;
import com.desha.Repositories.GuestRepository;
import com.desha.Repositories.InviteRepository;
import com.desha.Repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.LockModeType;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/reservations")
public class ReservationController {

    private GuestRepository guestRepository;
    private ReservationRepository reservationRepository;
    private InviteRepository invitesRepository;

    @Autowired
    public ReservationController(GuestRepository guestRepository, ReservationRepository repository, InviteRepository invitesRepository) {
        this.guestRepository = guestRepository;
        this.reservationRepository = repository;
        this.invitesRepository = invitesRepository;
    }

    @RequestMapping(value = "/all")
    List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    @RequestMapping(value = "/getOne/{id}")
    public Reservation getById(@PathVariable int id) {
        return reservationRepository.findById(id);
    }

    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    Reservation addReservation(@RequestBody Reservation reservation) {
        Timestamp sq = reservation.getStart();
        sq.setTime(1000 * (long) Math.floor(sq.getTime() / 1000));
        reservation.setStart(sq);
        reservationRepository.save(reservation);
        return reservationRepository.findByGuestEmailAndRestaurantNameAndStartAndForh(reservation.getGuestEmail(), reservation.getRestaurantName(), reservation.getStart(), reservation.getForh());
    }

    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    void deleteReservation(@RequestBody Reservation reservation) {
        ArrayList<Invite> invitesToDelete = invitesRepository.findByReservationId(reservation.getId());
        for (Invite i : invitesToDelete) {
            invitesRepository.delete(i);
        }
        reservationRepository.delete(reservation);
    }

    @RequestMapping(value = "/allActive/{email:.+}")
    List<Reservation> getAllActive(@PathVariable String email) {
        return reservationRepository.findByGuestEmailAndStartAfter(email, new Date());
    }

    @RequestMapping(value = "/allInactive/{email:.+}")
    List<Reservation> getAllInactive(@PathVariable String email) {
        if (email != null) {

            Guest host = guestRepository.findByEmail(email);
            ArrayList<Reservation> finalListRest = new ArrayList<>();
            finalListRest = reservationRepository.findByGuestEmailAndStartBefore(host.getEmail(), new Date());

            if (finalListRest.isEmpty())
                return null;
            else
                return finalListRest;
        } else return null;
    }
}
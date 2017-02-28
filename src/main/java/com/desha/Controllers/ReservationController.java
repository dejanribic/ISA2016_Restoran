package com.desha.Controllers;

import com.desha.Beans.Guest;
import com.desha.Beans.Reservation;
import com.desha.Repositories.GuestRepository;
import com.desha.Repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/reservations")
public class ReservationController {

    private GuestRepository guestRepository;
    private ReservationRepository repository;

    @Autowired
    public ReservationController(GuestRepository guestRepository, ReservationRepository repository) {
        this.guestRepository = guestRepository;
        this.repository = repository;
    }

    @RequestMapping(value = "/all")
    List<Reservation> getAll() {
        return repository.findAll();
    }

    @RequestMapping(value = "/getOne/{id}")
    public Reservation getById(@PathVariable int id) {
        return repository.findById(id);
    }


    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    Reservation addReservation(@RequestBody Reservation reservation) {
        Timestamp sq = reservation.getStart();
        sq.setTime(1000 * (long) Math.floor(sq.getTime() / 1000));
        reservation.setStart(sq);
        repository.save(reservation);
        return repository.findByGuestEmailAndRestaurantNameAndStartAndForh(reservation.getGuestEmail(), reservation.getRestaurantName(), reservation.getStart(), reservation.getForh());
    }

    // URADJENO

    @RequestMapping(value = "/allActive/{email:.+}")
    List<Reservation> getAllActive(@PathVariable String email) {
        Guest host = guestRepository.findByEmail(email);
        //return null;
        //return repository.findByHostAndDateTimeAfter(host, new Date());
        return repository.findByGuestEmailAndStartAfter(email, new Date());
    }

    @RequestMapping(value = "/allInactive/{email:.+}")
    List<Reservation> getAllInactive(@PathVariable String email) {
        if (email != null) {
            Guest host = guestRepository.findByEmail(email);
            ArrayList<Reservation> res = new ArrayList<>();
            res = repository.findByGuestEmailAndStartBefore(host.getEmail(), new Date());
            if (res.isEmpty())
                return null;
            else
                return res;
        } else return null;
    }
}

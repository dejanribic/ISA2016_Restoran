package com.desha.Repositories;

import com.desha.Beans.Reservation;
import com.desha.Beans.ReservationKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, ReservationKey> {

    //List<Reservation> findByHostAndDateTimeBefore(Guest host, Date date);

    //List<Reservation> findByHostAndDateTimeAfter(Guest host, Date date);

    ArrayList<Reservation> findByGuestEmailAndStartAfter(String guestEmail, Date date);

    Reservation findByGuestEmailAndRestaurantNameAndStartAndForh(String guestEmail, String restaurantName, Date start, int forh);
}

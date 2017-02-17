package com.desha.Repositories;

import com.desha.Beans.Guest;
import com.desha.Beans.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByHostAndDateTimeBefore(Guest host, Date date);

    List<Reservation> findByHostAndDateTimeAfter(Guest host, Date date);
}

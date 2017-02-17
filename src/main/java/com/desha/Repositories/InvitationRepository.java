package com.desha.Repositories;

import com.desha.Beans.Guest;
import com.desha.Beans.Invitation;
import com.desha.Beans.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    List<Invitation> findByInvited(Guest guest);

    List<Invitation> findByReservation(Reservation reservation);

    Invitation findByInvitedAndReservation(Guest invited, Reservation reservation);
}

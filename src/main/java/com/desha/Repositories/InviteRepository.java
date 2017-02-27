package com.desha.Repositories;

import com.desha.Beans.Invite;
import com.desha.Beans.InviteKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InviteRepository extends JpaRepository<Invite, InviteKey> {

//    List<Invite> findByInvited(Guest guest);

    //List<Invite> findByReservation(Reservation reservation);

    //  Invite findByInvitedAndReservation(Guest invited, Reservation reservation);
}

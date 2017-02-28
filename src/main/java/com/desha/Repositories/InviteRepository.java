package com.desha.Repositories;

import com.desha.Beans.Invite;
import com.desha.Beans.InviteKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;

@Repository
public interface InviteRepository extends JpaRepository<Invite, InviteKey> {
    ArrayList<Invite> findByReservationId(int id);

    ArrayList<Invite> findByFriendEmailAndAccepted(String email, boolean accepted);

    Invite findByReservationIdAndGuestEmailAndFriendEmailAndRestaurantNameAndAccepted(int reservationId, String guestEmail, String friendEmail, String restaurantName, boolean accepted);

    ArrayList<Invite> findByFriendEmailAndAcceptedAndStartBefore(String email, boolean b, Date date);
}

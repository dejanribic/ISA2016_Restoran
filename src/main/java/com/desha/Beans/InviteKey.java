package com.desha.Beans;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by DEJAN on 27/02/17 at 14:03.
 */
@Embeddable
public class InviteKey implements Serializable {

    @Id
    @Column(name = "guest_mail")
    private String guestEmail;

    @Id
    @Column(name = "friend_mail")
    private String friendEmail;

    @Id
    @Column(name = "reservation_id")
    private int reservationId;

    @Id
    @Column(name = "restaurant_name")
    private String restaurantName;

    public InviteKey() {
    }

    public InviteKey(String guestEmail, String friendEmail, int reservationId, String restaurantName) {
        this.guestEmail = guestEmail;
        this.friendEmail = friendEmail;
        this.reservationId = reservationId;
        this.restaurantName = restaurantName;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public String getFriendEmail() {
        return friendEmail;
    }

    public void setFriendEmail(String friendEmail) {
        this.friendEmail = friendEmail;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
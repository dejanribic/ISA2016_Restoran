package com.desha.Beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@IdClass(InviteKey.class)
public class Invite implements Serializable {

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

    @Column
    private int accepted;

    public Invite() {
    }

    public Invite(String guestEmail, String friendEmail, int reservationId, String restaurantName, int accepted) {
        this.guestEmail = guestEmail;
        this.friendEmail = friendEmail;
        this.reservationId = reservationId;
        this.restaurantName = restaurantName;
        this.accepted = accepted;
    }

    @Override
    public String toString() {
        return "Invite{" +
                "guestEmail='" + guestEmail + '\'' +
                ", friendEmail='" + friendEmail + '\'' +
                ", reservationId=" + reservationId +
                ", restaurantName='" + restaurantName + '\'' +
                ", accepted=" + accepted +
                '}';
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

    public int isAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }
}

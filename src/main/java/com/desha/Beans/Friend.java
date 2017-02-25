package com.desha.Beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

/**
 * Created by DEJAN on 24/02/17 at 20:05.
 */

@Entity
@IdClass(FriendKey.class)
public class Friend implements Serializable {

    @Id
    @Column(name = "guest_mail")
    private String guestEmail;

    @Id
    @Column(name = "friend_mail")
    private String friendEmail;


    @Column
    private int accepted;

    public Friend(String guest_email, String friend_email, int accepted) {
        this.guestEmail = guest_email;
        this.friendEmail = friend_email;
        this.accepted = accepted;
    }

    public Friend() {
    }

    public String getGuestMail() {
        return guestEmail;
    }

    public void setGuestMail(String guestMail) {
        this.guestEmail = guestMail;
    }

    public String getFriendMail() {
        return friendEmail;
    }

    public void setFriendMail(String friendMail) {
        this.friendEmail = friendMail;
    }

    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }
}

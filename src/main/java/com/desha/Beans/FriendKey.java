package com.desha.Beans;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by DEJAN on 2/22/2017.
 */
@Embeddable
public class FriendKey implements Serializable {

    @Id
    @Column(name = "guest_mail")
    //@JoinColumn(foreignKey = @ForeignKey(name = "fk_Guest_has_Guest_Guest"))
    private String guestEmail;

    @Id
    @Column(name = "friend_mail")
    //@JoinColumn(foreignKey = @ForeignKey(name = "fk_Guest_has_Guest_Guest1"))
    private String friendEmail;

    public FriendKey() {
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
}
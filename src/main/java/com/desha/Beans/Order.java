package com.desha.Beans;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Viktor on 2/27/2017.
 */

@Entity
@Table(name = "MenuOrder")
@IdClass(OrderKey.class)
public class Order implements Serializable {

    @Id
    private int num;

    @Column
    private Boolean done;

    @Column
    private Boolean accepted;

    @Column
    private Boolean paid;

    @Id
    @Column(name="reservation_id")
    private String resid;

    @Id
    @Column(name="restaurant_name")
    private String resname;

    @Id
    @Column(name="guest_email")
    private String gmail;

    public Order(int num, Boolean done, Boolean accepted, Boolean paid, String resid, String resname, String gmail) {
        this.num = num;
        this.done = done;
        this.accepted = accepted;
        this.paid = paid;
        this.resid = resid;
        this.resname = resname;
        this.gmail = gmail;
    }

    public Order() {
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public String getResid() {
        return resid;
    }

    public void setResid(String resid) {
        this.resid = resid;
    }

    public String getResname() {
        return resname;
    }

    public void setResname(String resname) {
        this.resname = resname;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }
}

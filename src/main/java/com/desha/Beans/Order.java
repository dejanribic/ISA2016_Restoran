package com.desha.Beans;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Viktor on 2/27/2017.
 */

@Entity
@Table(name = "Menu_Order")
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
    @Column(name = "reservation_id")
    private int resid;

    @Id
    @Column(name = "restaurant_name")
    private String resname;

    @Id
    @Column(name = "guest_email")
    private String gmail;

    @Column
    private String empolyeeemail;


    public Order(int num, Boolean done, Boolean accepted, Boolean paid, int resid, String resname, String gmail, String empolyeeemail) {
        this.num = num;
        this.done = done;
        this.accepted = accepted;
        this.paid = paid;
        this.resid = resid;
        this.resname = resname;
        this.gmail = gmail;
        this.empolyeeemail = empolyeeemail;
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

    public int getResid() {
        return resid;
    }

    public void setResid(int resid) {
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
    public String getEmpolyeeemail() {
        return empolyeeemail;
    }

    public void setEmpolyeeemail(String empolyeeemail) {
        this.empolyeeemail = empolyeeemail;
    }

}

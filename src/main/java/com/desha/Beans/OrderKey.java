package com.desha.Beans;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Viktor on 2/27/2017.
 */
@Embeddable
public class OrderKey implements Serializable

    {

    @Id
    private int num;

    @Id
    @Column(name="reservation_id")
    private int resid;

    @Id
    @Column(name="restaurant_name")
    private String resname;

    @Id
    @Column(name="guest_email")
    private String gmail;

    public OrderKey(int num, int resid, String resname, String gmail) {
        this.num = num;

        this.resid = resid;
        this.resname = resname;
        this.gmail = gmail;
    }

    public OrderKey() {
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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
}

package com.desha.Beans;

import javax.persistence.Id;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Viktor on 2/28/2017.
 */
@Embeddable
public class Work_ScheduleKey implements Serializable {

    @Id
    private int num;



    @Id
    @Column(name = "employee_email")
    private String email;

    @Id
    @Column(name = "restaurant_name")
    private String resname;

    @Id
    @Column(name = "region_id")
    private int id;

    public Work_ScheduleKey(int num, String email, String resname, int id) {
        this.num = num;

        this.email = email;
        this.resname = resname;
        this.id = id;
    }

    public Work_ScheduleKey() {
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResname() {
        return resname;
    }

    public void setResname(String resname) {
        this.resname = resname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

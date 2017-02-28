package com.desha.Beans;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.DateTimeException;

/**
 * Created by Viktor on 2/28/2017.
 */
@Entity
@IdClass(Work_SheduleKey.class)
public class Work_Shedule  implements Serializable {

    @Id
    private int num;

    @Column
    private Timestamp start;

    @Column
    private Timestamp end;

    @Id
    @Column(name = "employee_email")
    private String email;

    @Id
    @Column(name = "restaurant_name")
    private String resname;

    @Id
    @Column(name = "region_id")
    private int id;

    public Work_Shedule(int num, Timestamp start, Timestamp end, String email, String resname, int id) {
        this.num = num;
        this.start = start;
        this.end = end;
        this.email = email;
        this.resname = resname;
        this.id = id;
    }

    public Work_Shedule() {
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
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

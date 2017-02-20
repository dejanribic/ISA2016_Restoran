package com.desha.Beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Nenad on 2/19/2017.
 */
@Entity
public class Sys_Manager {

    @Id
    private String email;
    @Column
    private String password;

    public Sys_Manager() {
    }

    public Sys_Manager(String email, String password) {
        this.email = email;
        this.password = password;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

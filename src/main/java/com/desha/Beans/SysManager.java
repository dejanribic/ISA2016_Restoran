package com.desha.Beans;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Nenad on 2/19/2017.
 */
@Entity
public class SysManager {

    @Column
    private String email;
    @Column
    private String password;
}

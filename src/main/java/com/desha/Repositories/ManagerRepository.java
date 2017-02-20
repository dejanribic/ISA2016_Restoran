package com.desha.Repositories;

import com.desha.Beans.Manager;

/**
 * Created by Nenad on 2/20/2017.
 */
public interface ManagerRepository {

    Manager findByEmailAndPassword(String email, String password);

    Manager findByEmail(String email);
}

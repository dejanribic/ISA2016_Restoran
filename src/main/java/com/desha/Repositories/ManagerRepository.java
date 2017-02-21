package com.desha.Repositories;

import com.desha.Beans.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Nenad on 2/20/2017 at 21:06.
 */
public interface ManagerRepository extends JpaRepository<Manager, String> {
    Manager findByEmail(String email);
}
package com.desha.Repositories;

import com.desha.Beans.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Nenad on 2/20/2017 at 21:06.
 */
@Repository
public interface ManagerRepository extends JpaRepository<Manager, String> {

    Manager findByEmailAndPassword(String email, String password);

    Manager findByEmail(String email);

}
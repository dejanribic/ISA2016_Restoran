package com.desha.Repositories;

import com.desha.Beans.Sys_Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Nenad on 2/19/2017.
 */
@Repository
public interface Sys_ManagerRepository extends JpaRepository<Sys_Manager, Long> {

    Sys_Manager findByEmailAndPassword(String email, String password);

    Sys_Manager findByEmail(String email);

}


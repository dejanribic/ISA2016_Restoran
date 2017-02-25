package com.desha.Repositories;

import com.desha.Beans.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface GuestRepository extends JpaRepository<Guest, Long> {

    Guest findByEmailAndPassword(String email, String password);

    Guest findByEmail(String email);

    ArrayList<Guest> findByName(String name);

    ArrayList<Guest> findByNameAndSurname(String name, String surname);
}

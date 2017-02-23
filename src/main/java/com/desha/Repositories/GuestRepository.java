package com.desha.Repositories;

import com.desha.Beans.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {

    Guest findByEmailAndPassword(String email, String password);

    Guest findByEmail(String email);

}

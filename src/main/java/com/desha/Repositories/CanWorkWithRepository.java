package com.desha.Repositories;

/**
 * Created by Viktor on 2/25/2017.
 */
import com.desha.Beans.Can_Work_With;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CanWorkWithRepository extends JpaRepository<Can_Work_With, String> {

        Can_Work_With findByEmail(String empolyee_email);

        }
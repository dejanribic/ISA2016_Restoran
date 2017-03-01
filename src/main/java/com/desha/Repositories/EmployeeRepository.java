package com.desha.Repositories;

/**
 * Created by Viktor on 2/25/2017.
 */
import com.desha.Beans.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

    Employee findByEmailAndPassword(String email, String password);

    Employee findByEmail(String email);


    Employee findByEmailAndRestaurantName(String email, String resname);
}

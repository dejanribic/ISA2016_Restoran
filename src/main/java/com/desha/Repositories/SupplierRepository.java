package com.desha.Repositories;
import com.desha.Beans.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Nenad on 2/20/2017.
 */
public interface SupplierRepository extends JpaRepository<Supplier, String>{

    Supplier findByEmailAndPassword(String email, String password);
    Supplier findByEmail(String email);

}

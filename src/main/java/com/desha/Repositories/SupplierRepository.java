package com.desha.Repositories;
import com.desha.Beans.Supplier;

/**
 * Created by Nenad on 2/20/2017.
 */
public interface SupplierRepository {

    Supplier findByEmailAndPassword(String email, String password);

    Supplier findByEmail(String email);

}

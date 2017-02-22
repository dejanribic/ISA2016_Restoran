package com.desha.Controllers;

import com.desha.Beans.Supplier;
import com.desha.Repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Nenad on 2/22/2017.
 */
@RestController
@RequestMapping(value = "/supplier")
public class SupplierController {

    private SupplierRepository repository;

    @Autowired
    public SupplierController(SupplierRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Supplier> getAll() { return repository.findAll(); }

    @RequestMapping(value = "/getByEmail/{email}")
    public Supplier getByName(@PathVariable String email) {
        return repository.findByEmail(email);
    }


    @RequestMapping(value = "/exists", method = RequestMethod.PUT)
    public Supplier exists(@RequestBody Supplier sentManager) {
        Supplier restaurant = repository.findByEmail(sentManager.getEmail());
        return restaurant;
    }

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public void create(@RequestBody Supplier newMenu_Item) {
        repository.save(newMenu_Item);
    }

}

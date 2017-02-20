package com.desha.Controllers;

import com.desha.Beans.Manager;
import com.desha.Repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Nenad on 2/20/2017.
 */
@RestController
@RequestMapping(value = "/managers")
public class ManagerController {

    private ManagerRepository repository;

    @Autowired
    public ManagerController(ManagerRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Manager> getAll() { return repository.findAll(); }

    @RequestMapping(value = "/getByEmail/{name}")
    public Manager getByName(@PathVariable String name) {
        return repository.findByEmail(name);
    }


    @RequestMapping(value = "/exists", method = RequestMethod.PUT)
    public Manager exists(@RequestBody Manager sentManager) {
        Manager restaurant = repository.findByEmail(sentManager.getEmail());
        return restaurant;
    }

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public void create(@RequestBody Manager newManager) {
        repository.save(newManager);
    }

}

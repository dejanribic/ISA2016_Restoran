package com.desha.Controllers;

import com.desha.Beans.Sys_Manager;
import com.desha.Repositories.Sys_ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Nenad on 2/19/2017.
 */
@RestController
@RequestMapping(value = "/sysman")
public class Sys_ManagerController {

    private Sys_ManagerRepository repository;

    @Autowired
    public Sys_ManagerController(Sys_ManagerRepository repository) {
        this.repository = repository;
    }


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Sys_Manager> getAll() {
        return repository.findAll();
    }

    @RequestMapping(value = "/getOne/{id}")
    public Sys_Manager getById(@PathVariable long id) {
        return repository.findOne(id);
    }


    @RequestMapping(value = "/exists", method = RequestMethod.PUT)
    public Sys_Manager exists(@RequestBody Sys_Manager sentUser) {
        Sys_Manager user = repository.findByEmailAndPassword(sentUser.getEmail(), sentUser.getPassword());
        System.out.println(user);

        List<Sys_Manager> listSvihUsera = repository.findAll();
        for (Sys_Manager temp : listSvihUsera) {
            System.out.println(temp.getEmail());
            System.out.println(temp.getPassword());
        }
        return user;
    }

    @RequestMapping(value = "/existsEmail", method = RequestMethod.PUT)
    public boolean existsEmail(@RequestBody String email) {
        Sys_Manager user = repository.findByEmail(email);
        if (user == null)
            return false;
        else
            return true;
    }


    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public void create(@RequestBody Sys_Manager newUser) {
        repository.save(newUser);
    }

}
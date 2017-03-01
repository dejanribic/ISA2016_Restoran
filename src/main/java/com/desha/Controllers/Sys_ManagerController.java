package com.desha.Controllers;

import com.desha.Beans.ReturnMessage;
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
    public Sys_Manager exists(@RequestBody Sys_Manager sentSys_Manager) {
        Sys_Manager sys_manager = repository.findByEmailAndPassword(sentSys_Manager.getEmail(), sentSys_Manager.getPassword());
        return sys_manager;
    }

    @RequestMapping(value = "/existsEmail", method = RequestMethod.PUT)
    public boolean existsEmail(@RequestBody String email) {
        Sys_Manager sys_manager = repository.findByEmail(email);
        if (sys_manager == null)
            return false;
        else
            return true;
    }


    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public ReturnMessage create(@RequestBody Sys_Manager newSys_Manager) {
        ReturnMessage ret = new ReturnMessage();
        newSys_Manager.setPassword("1");
        if(repository.findByEmail(newSys_Manager.getEmail())!=null){
            ret.setMessage("System manager with that email already exists.");
        }else{
            if(repository.save(newSys_Manager)!=null){
                ret.setMessage("System manager "+newSys_Manager.getEmail() +" registered.");
            }
        }

        return ret;
    }

}
package com.desha.Controllers;

import com.desha.Beans.Employee;
import com.desha.Beans.Work_Schedule;
import com.desha.Repositories.EmployeeRepository;
import com.desha.Repositories.Work_SheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Viktor on 2/28/2017.
 */

@RestController
@RequestMapping(value = "/raspored")
public class RasporedController {


    private EmployeeRepository emprepo;
    private Work_SheduleRepository wsrepo;

    @Autowired
    public RasporedController(EmployeeRepository emprepo , Work_SheduleRepository wsrepo)
    {   this.wsrepo = wsrepo;
        this.emprepo = emprepo;
    }


    @RequestMapping(value = "/getEmployee/{email}")
    Employee getemp(@PathVariable String email) {
        return emprepo.findByEmail(email);
    }

    @RequestMapping(value = "/smene/{email}/{resname}")
    List<Work_Schedule> getemp(@PathVariable String email, @PathVariable String resname) {

        System.out.print("**************");
        System.out.print(email);
        System.out.print(resname);
        System.out.print("**************");

        return wsrepo.findByEmailAndResname(email , resname);

    }



}

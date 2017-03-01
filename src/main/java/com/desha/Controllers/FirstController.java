package com.desha.Controllers;

import com.desha.Beans.Employee;
import com.desha.Beans.Order_has_Menu_Item;
import com.desha.Repositories.EmployeeRepository;
import com.desha.Repositories.Work_SheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.EmbeddedServerPortFileWriter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Viktor on 3/1/2017.
 */
@RestController
@RequestMapping(value = "/first")
public class FirstController {

    private EmployeeRepository emprepo;

    @Autowired
    public FirstController(EmployeeRepository emprepo )
    {
        this.emprepo = emprepo;
    }

    @RequestMapping(value = "/change/{email}/{resname}/{newmail}", method = RequestMethod.GET)
    Employee change(@PathVariable String email , @PathVariable String resname , @PathVariable String newmail)
    {
        Employee temp = emprepo.findByEmailAndRestaurantName(email,resname);


        System.out.print("**************");
        System.out.println(email + resname + newmail);
        System.out.print("**************");


        temp.setFirstlog(true);
        temp.setPassword(newmail);

        emprepo.save(temp);

        return null;
    }


}

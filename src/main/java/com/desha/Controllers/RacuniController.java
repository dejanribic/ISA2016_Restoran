package com.desha.Controllers;

import com.desha.Beans.*;
import com.desha.Repositories.EmployeeRepository;
import com.desha.Repositories.Menu_ItemRepository;
import com.desha.Repositories.OrderRepository;
import com.desha.Repositories.Order_has_Menu_ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Viktor on 2/27/2017.
 */
@RestController
@RequestMapping(value = "/racuni")
public class RacuniController {

    private OrderRepository orderrepo;
    private EmployeeRepository emprepo;

    @Autowired
    public RacuniController(OrderRepository orderrepo , EmployeeRepository emprepo  )  {
        this.orderrepo = orderrepo;
        this.emprepo = emprepo;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    List<Order> getAll() {  return orderrepo.findAll();}

    @RequestMapping(value = "/naplati/{num}/{resid}/{resname}/{gmail}", method = RequestMethod.GET)
    List<Order> naplati(@PathVariable int num , @PathVariable  int resid , @PathVariable  String resname , @PathVariable  String gmail)
    {
        Order temp = orderrepo.findByNumAndResidAndResnameAndGmail(num , resid , resname , gmail );

        temp.setPaid(true);

        orderrepo.save(temp);

        return orderrepo.findAll();
    }

    @RequestMapping(value = "/accepted/{num}/{resid}/{resname}/{gmail}/{employeeemail}", method = RequestMethod.GET)
    List<Order> accepted(@PathVariable int num , @PathVariable  int resid , @PathVariable  String resname , @PathVariable  String gmail , @PathVariable String employeeemail)
    {
        Order temp = orderrepo.findByNumAndResidAndResnameAndGmail(num , resid , resname , gmail );

        Employee e = emprepo.findByEmailAndRestaurantName(employeeemail,resname);


        temp.setAccepted(true);
        temp.setEmpolyeeemail(e.getEmail());

        System.out.print(temp.getAccepted());
        System.out.print(temp.getEmpolyeeemail());

        orderrepo.save(temp);

        return orderrepo.findAll();
    }

}

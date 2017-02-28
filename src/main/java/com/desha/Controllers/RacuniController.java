package com.desha.Controllers;

import com.desha.Beans.Menu_Item;
import com.desha.Beans.Order_has_Menu_Item;
import com.desha.Beans.Reservation;
import com.desha.Repositories.Menu_ItemRepository;
import com.desha.Repositories.OrderRepository;
import com.desha.Repositories.Order_has_Menu_ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.desha.Beans.Order;

import java.util.List;

/**
 * Created by Viktor on 2/27/2017.
 */
@RestController
@RequestMapping(value = "/racuni")
public class RacuniController {

    private OrderRepository orderrepo;

    @Autowired
    public RacuniController(OrderRepository orderrepo )  {
        this.orderrepo = orderrepo;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    List<Order> getAll() {  return orderrepo.findAll();}

    @RequestMapping(value = "/naplati/{num}/{resid}/{resname}/{gmail}", method = RequestMethod.GET)
    List<Order> naplati(@PathVariable int num , @PathVariable  int resid , @PathVariable  String resname , @PathVariable  String gmail)
    {
        Order temp = orderrepo.findByNumAndResidAndResnameAndGmail(num , resid , resname , gmail );



        temp.setPaid(true);

        System.out.print("*****************");
        System.out.print(temp.getNum());
        System.out.print("*****************");


        orderrepo.save(temp);

        return orderrepo.findAll();
    }

}

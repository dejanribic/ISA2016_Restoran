package com.desha.Controllers;

import com.desha.Beans.Menu_Item;
import com.desha.Beans.Order_has_Menu_Item;
import com.desha.Repositories.Menu_ItemRepository;
import com.desha.Repositories.Order_has_Menu_ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Viktor on 2/26/2017.
 */
@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private Order_has_Menu_ItemRepository repository;
    private Menu_ItemRepository menurepo;

    @Autowired
    public OrderController(Order_has_Menu_ItemRepository repository , Menu_ItemRepository menurepo)  {
        this.repository = repository;
        this.menurepo = menurepo;

    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    List<Order_has_Menu_Item> getAll() { return repository.findAll();}


    @RequestMapping(value = "done/{order_num}/{reservation_id}/{restaurant_name}/{guest_email}/{menu_item_name}")
    public  List<Order_has_Menu_Item> done(@PathVariable int order_num, @PathVariable int reservation_id , @PathVariable String restaurant_name, @PathVariable String guest_email , @PathVariable String menu_item_name ) {
        Order_has_Menu_Item temp = repository.findByNumAndIdAndRestnameAndEmailAndMenuitemname
                (  order_num ,  reservation_id ,  restaurant_name ,  guest_email ,  menu_item_name );




        temp.setDone(true);

        /*
        System.out.println(temp.getId());
        System.out.println(temp.getEmail());
        System.out.println(temp.getMenuitemname());
        System.out.println(temp.getNum());
        System.out.println(temp.getRestname());
        System.out.println(temp.getDone());
        */

    //    repository.save(temp);

        return repository.findAll();

    }

    @RequestMapping(value = "checktype/{name}/{restname}")
    public  Menu_Item check(@PathVariable String name ,@PathVariable String restname) {

        Menu_Item temp = menurepo.findByNameAndRestname(name,restname);
        System.out.println(temp.getType_name());

        return temp;

    }

}

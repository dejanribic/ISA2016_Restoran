package com.desha.Controllers;

import com.desha.Beans.Menu_Item;
import com.desha.Beans.Order;
import com.desha.Beans.Order_has_Menu_Item;
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
 * Created by Viktor on 2/26/2017.
 */
@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private Order_has_Menu_ItemRepository repository;
    private Menu_ItemRepository menurepo;
    private OrderRepository orderrepo ;

    @Autowired
    public OrderController(Order_has_Menu_ItemRepository repository , Menu_ItemRepository menurepo , OrderRepository orderrepo)  {
        this.repository = repository;
        this.menurepo = menurepo;
        this.orderrepo = orderrepo;

    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    List<Order_has_Menu_Item> getAll() { return repository.findAll();}


    @RequestMapping(value = "done/{order_num}/{reservation_id}/{restaurant_name}/{guest_email}/{menu_item_name}")
    public  List<Order_has_Menu_Item> done(@PathVariable int order_num, @PathVariable int reservation_id , @PathVariable String restaurant_name, @PathVariable String guest_email , @PathVariable String menu_item_name ) {
        Order_has_Menu_Item temp = repository.findByNumAndIdAndRestnameAndEmailAndMenuitemname
                (  order_num ,  reservation_id ,  restaurant_name ,  guest_email ,  menu_item_name );

        temp.setDone(true);



        List<Order_has_Menu_Item> fml = repository.findByNumAndIdAndRestnameAndEmail(order_num, reservation_id ,restaurant_name,guest_email );

        int flag = 0 ;

        for(Order_has_Menu_Item o : fml)
        {
            if(!o.getDone())
                flag=1;
        }

        if (flag == 0)
        {
            Order plsradi = orderrepo.findByNumAndResidAndResnameAndGmail(order_num,reservation_id ,restaurant_name,guest_email );
            plsradi.setDone(true);
            orderrepo.save(plsradi);
        }

        repository.save(temp);

        return repository.findByRestname(temp.getRestname());

    }

    @RequestMapping(value = "checktype/{name}/{restname}")
    public  Menu_Item check(@PathVariable String name ,@PathVariable String restname) {

        Menu_Item temp = menurepo.findByNameAndRestname(name,restname);
        System.out.println(temp.getType_name());

        return temp;

    }

    @RequestMapping(value = "accept/{order_num}/{reservation_id}/{restaurant_name}/{guest_email}/{menu_item_name}/{employeeemail}")
    public  List<Order_has_Menu_Item> accept(@PathVariable int order_num, @PathVariable int reservation_id , @PathVariable String restaurant_name, @PathVariable String guest_email , @PathVariable String menu_item_name , @PathVariable String employeeemail ) {
        Order_has_Menu_Item temp = repository.findByNumAndIdAndRestnameAndEmailAndMenuitemname
                (order_num, reservation_id, restaurant_name, guest_email, menu_item_name);

        temp.setAccepted(true);
        temp.setEmployeeemail(employeeemail);

        repository.save(temp);

        System.out.print(temp.getMenuitemname());
        System.out.print(temp.getAccepted());
        System.out.print(temp.getEmployeeemail());



        return repository.findByRestname(temp.getRestname());


    }

}

package com.desha.Controllers;

import com.desha.Beans.*;
import com.desha.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {

    private RestaurantRepository restaurantRepository;
    private Restaurant_RatingRepository restaurant_ratingRepository;
    private OrderRepository orderRepository ;
    private Menu_ItemRepository menu_itemRepository;
    private Order_has_Menu_ItemRepository orderHasMenuItemRepository;
    private Menu_Item_RatingRepository menu_item_ratingRepository;
   private Employee_RatingRepository employee_ratingRepository;

    @Autowired
    public RestaurantController(RestaurantRepository restaurantRepository,
                                Restaurant_RatingRepository restaurant_ratingRepository ,
                                OrderRepository orderRepository , Menu_ItemRepository menu_itemRepository , Order_has_Menu_ItemRepository orderHasMenuItemRepository , Menu_Item_RatingRepository menu_item_ratingRepository, Employee_RatingRepository employee_ratingRepository) {
        this.restaurantRepository = restaurantRepository;
        this.restaurant_ratingRepository = restaurant_ratingRepository;
        this.orderRepository = orderRepository;
        this.menu_itemRepository = menu_itemRepository;
        this.orderHasMenuItemRepository = orderHasMenuItemRepository;
        this.menu_item_ratingRepository = menu_item_ratingRepository;
        this.employee_ratingRepository = employee_ratingRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @RequestMapping(value = "/getByName/{name}")
    public Restaurant getByName(@PathVariable String name) {
        Restaurant ret = restaurantRepository.findByName(name);
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        attr.setAttribute("activeRestaurant", ret, ServletRequestAttributes.SCOPE_SESSION);
        return ret;
    }

    @RequestMapping(value = "/getActive", method = RequestMethod.GET)
    public Restaurant getActive() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        Restaurant ret = (Restaurant) attr.getAttribute("activeRestaurant", ServletRequestAttributes.SCOPE_SESSION);
        return ret;
    }

    @RequestMapping(value = "/oceni/{restrating}/{konobarrating}/{jelorating}/{gmail}/{restname}/{resid}", method = RequestMethod.GET)
    public Restaurant oceni(@PathVariable String restrating , @PathVariable String konobarrating , @PathVariable String jelorating , @PathVariable String gmail , @PathVariable String restname , @PathVariable int resid  ) {

        int r = Integer.parseInt(restrating);
        int k = Integer.parseInt(konobarrating);
        int j = Integer.parseInt(jelorating);

        System.out.println(restname);

        Restaurant_Rating rr = new Restaurant_Rating(restname,gmail,r);

        restaurant_ratingRepository.save(rr);

        List<Menu_Item> milist = menu_itemRepository.findByRestname(restname);

        for (Menu_Item m : milist) {
            List<Order_has_Menu_Item> ohmilist = orderHasMenuItemRepository.findByIdAndRestnameAndEmailAndMenuitemname(resid, restname, gmail, m.getName());
            for(Order_has_Menu_Item oh : ohmilist)
            {
                Menu_Item_Rating mir = new Menu_Item_Rating( oh.getMenuitemname() , restname , gmail , j);
                menu_item_ratingRepository.save(mir);

            }
        }

        List<Order> mo = orderRepository.findByResidAndResnameAndGmail(resid, restname, gmail);
        for(Order ord : mo)
        {
            Employee_Rating er = new Employee_Rating(restname , ord.getEmpolyeeemail() , gmail , k);

            employee_ratingRepository.save(er);
        }


        return null;
    }

    @RequestMapping(value = "/getRating", method = RequestMethod.GET)
    public double getRating() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        Restaurant res = (Restaurant) attr.getAttribute("activeRestaurant", ServletRequestAttributes.SCOPE_SESSION);
        List<Restaurant_Rating> ratings = restaurant_ratingRepository.findByRestaurantName(res.getName());
        ArrayList<Restaurant_Rating> rts = new ArrayList<>(ratings);
        double rating = 0;
        for (Restaurant_Rating rtg : rts) {
            rating += rtg.getRating();

        }
        if (rts.size() != 0) {
            rating /= rts.size();
        }
        return rating;
    }


    @RequestMapping(value = "/exists", method = RequestMethod.PUT)
    public Restaurant exists(@RequestBody Restaurant sentRestaurant) {
        Restaurant restaurant = restaurantRepository.findByName(sentRestaurant.getName());
        return restaurant;
    }

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public void create(@RequestBody Restaurant newRestaurant) {
        restaurantRepository.save(newRestaurant);
    }


}

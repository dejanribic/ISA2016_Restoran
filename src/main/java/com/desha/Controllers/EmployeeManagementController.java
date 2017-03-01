package com.desha.Controllers;

import com.desha.Beans.*;

import com.desha.Repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Nenad on 3/1/2017.
 */
@RestController
@RequestMapping(value = "/empman")
public class EmployeeManagementController {

    private EmployeeRepository employeeRepository;
    private EmployeeRatingRepository employeeRatingRepository;
    private CanWorkWithRepository canWorkWithRepository;
    private WorkScheduleRepository workScheduleRepository;
    private ReservationRepository reservationRepository;
    private Menu_ItemRepository menu_itemRepository;
    private Order_has_Menu_ItemRepository order_has_menu_itemRepository;
    private RegionRepository regionRepository;

    @Autowired
    public EmployeeManagementController(EmployeeRepository employeeRepository, EmployeeRatingRepository employeeRatingRepository, CanWorkWithRepository canWorkWithRepository, WorkScheduleRepository workScheduleRepository, ReservationRepository reservationRepository, Menu_ItemRepository menu_itemRepository, Order_has_Menu_ItemRepository order_has_menu_itemRepository, RegionRepository regionRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeRatingRepository = employeeRatingRepository;
        this.canWorkWithRepository = canWorkWithRepository;
        this.workScheduleRepository = workScheduleRepository;
        this.reservationRepository = reservationRepository;
        this.menu_itemRepository = menu_itemRepository;
        this.order_has_menu_itemRepository = order_has_menu_itemRepository;
        this.regionRepository = regionRepository;
    }

    @RequestMapping(value = "/restaurantEarnings", method = RequestMethod.PUT)
    public int restaurantEarnings(@RequestBody DateSpan dateSpan) {

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        Restaurant res = (Restaurant) attr.getAttribute("activeRestaurant", ServletRequestAttributes.SCOPE_SESSION);
        ArrayList<Menu_Item> mis = menu_itemRepository.findByRestname(res.getName());
        int earning = 0;
        ArrayList<Reservation> rs = reservationRepository.findByStartBetweenAndRestaurantName(dateSpan.getStart(), dateSpan.getEnd(), res.getName());
        ArrayList<Integer> ids = new ArrayList<>();
        for (Reservation r : rs)
            ids.add(r.getId());
        ArrayList<Order_has_Menu_Item> ohmis = order_has_menu_itemRepository.findByRestnameAndIdIn(res.getName(), ids);
        for (Order_has_Menu_Item ohmi : ohmis) {
            for (Menu_Item mi : mis) {
                if (mi.getName().equals(ohmi.getMenuitemname())) {
                    earning += mi.getPrice();
                    break;
                }
            }

        }

        return earning;
    }

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public Employee crate(@RequestBody Employee toAdd) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        Restaurant res = (Restaurant) attr.getAttribute("activeRestaurant", ServletRequestAttributes.SCOPE_SESSION);
        if (!employeeRepository.exists(new EmployeeKey(toAdd.getEmail(), res.getName()))) {
            toAdd.setRestaurantName(res.getName());
            toAdd.setPassword("");
            return employeeRepository.save(toAdd);
        }
        return null;
    }

    @RequestMapping(value = "/addSchedules", method = RequestMethod.PUT)
    public ReturnMessage addSchedules(@RequestBody ArrayList<Work_Schedule> schedules) {
        ReturnMessage ret = new ReturnMessage();
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        Restaurant res = (Restaurant) attr.getAttribute("activeRestaurant", ServletRequestAttributes.SCOPE_SESSION);
        ArrayList<Region> regions = regionRepository.findByResname(res.getName());
        if(regions.size()==0){
            ret.setMessage("No regions");
            return ret;
        }
        int cnt=0;
        int num=1;
        for (Work_Schedule ws : schedules) {
            ws.setResname(res.getName());
            ws.setNum(num++);
            ws.setId(regions.get(cnt++).getId());
            if(cnt>=regions.size()) cnt=0;
            workScheduleRepository.save(ws);

        }
        ret.setMessage("Schedules successfully added");
        return ret;
    }

    @RequestMapping(value = "/allWithEarningAndRating")
    public List<EmployeeWithEarningsAndRating> allWithEarningAndRating() {

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        Restaurant res = (Restaurant) attr.getAttribute("activeRestaurant", ServletRequestAttributes.SCOPE_SESSION);
        ArrayList<Employee> employees = employeeRepository.findByRestaurantName(res.getName());
        ArrayList<EmployeeWithEarningsAndRating> ret = new ArrayList<>();
        double rating = 0;
        int earnings = 0;
        boolean is = false;
        for (Employee e : employees) {
            is = isWaiter(e.getEmail());
            if (is) {
                rating = getRating(e.getEmail(), res.getName());
                earnings = getEarnings(e.getEmail(), res.getName());
            } else {
                rating = 0;
                earnings = 0;
            }
            ret.add(new EmployeeWithEarningsAndRating(e, rating, earnings, is));
        }
            /*
            "select sum(mi.price) from Reservation r inner join Order_Has_Menu_Item ohsmi inner join Menu_Item mi  inner join Work_Schedule ws where"+
            " r.id = ohsmi.reservation_id and r.restaurant_name=ohsmi.restaurant_name and mi.restaurant_name=r.restaurant_name " +
            "and ws.restaurant_name =r.restaurant_name and ohsmi.menu_item_name = mi.name and ws.start<= r.start and ws.end>= r.start "+
            "and ws.employee_email = :employeeEmail and ws.restaurant_name = :restaurantName ")*/


        return ret;
    }

    @RequestMapping(value = "/getSchedules")
    public List<Work_Schedule> getSchedules() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        Restaurant res = (Restaurant) attr.getAttribute("activeRestaurant", ServletRequestAttributes.SCOPE_SESSION);
        ArrayList<Work_Schedule> ret = workScheduleRepository.findByResname(res.getName());
        return ret;
    }


    private double getRating(String employeeEmail, String restaurantName) {
        ArrayList<Employee_Rating> ratings = employeeRatingRepository.findByRestaurantNameAndEmployeeEmail(restaurantName, employeeEmail);
        double rating = 0;
        for (Employee_Rating rtg : ratings) {
            rating += rtg.getRating();
        }
        if (ratings.size() != 0) {
            rating /= ratings.size();
        }
        return rating;

    }

    private int getEarnings(String employeeEmail, String restaurantName) {
        ArrayList<Work_Schedule> wss = workScheduleRepository.findByEmailAndResname(employeeEmail, restaurantName);
        ArrayList<Menu_Item> mis = menu_itemRepository.findByRestname(restaurantName);
        int earning = 0;
        for (Work_Schedule ws : wss) {
            ArrayList<Reservation> rs = reservationRepository.findByStartBetweenAndRestaurantName(ws.getStart(), ws.getEnd(), restaurantName);
            ArrayList<Integer> ids = new ArrayList<>();
            for (Reservation r : rs)
                ids.add(r.getId());
            ArrayList<Order_has_Menu_Item> ohmis = order_has_menu_itemRepository.findByRestnameAndIdIn(restaurantName, ids);
            for (Order_has_Menu_Item ohmi : ohmis) {
                for (Menu_Item mi : mis) {
                    if (mi.getName().equals(ohmi.getMenuitemname())) {
                        earning += mi.getPrice();
                        break;
                    }
                }

            }

        }
        return earning;

    }

    private boolean isWaiter(String employeeEmail) {
        Can_Work_With cww = canWorkWithRepository.findByEmail(employeeEmail);
        if (cww == null) return true;
        else return false;

    }


}

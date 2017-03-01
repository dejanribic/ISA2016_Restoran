package com.desha.Controllers;

import com.desha.Beans.*;
import com.desha.Repositories.Menu_ItemRepository;
import com.desha.Repositories.Menu_Item_RatingRepository;
import com.desha.Repositories.Menu_Item_TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nenad on 2/22/2017.
 */
@RestController
@RequestMapping(value = "/menu-item")
public class Menu_ItemController {
    private Menu_ItemRepository menuItemRepository;
    private Menu_Item_TypeRepository menuItemTypeRepository;
    private Menu_Item_RatingRepository mirRepository;

    @Autowired
    public Menu_ItemController(Menu_ItemRepository menuItemRepository, Menu_Item_TypeRepository menuItemTypeRepository, Menu_Item_RatingRepository mirRepository) {
        this.menuItemRepository = menuItemRepository;
        this.menuItemTypeRepository = menuItemTypeRepository;
        this.mirRepository = mirRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<MenuItemWithRating> getAll() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        Restaurant res = (Restaurant) attr.getAttribute("activeRestaurant", ServletRequestAttributes.SCOPE_SESSION);
        ArrayList<MenuItemWithRating> ret = new ArrayList<>();
        ArrayList<Menu_Item> menuItems = menuItemRepository.findByRestname(res.getName());
        double rating;
        for (Menu_Item mi : menuItems) {
            rating = getMenuItemRating(mi.getName());
            ret.add(new MenuItemWithRating(mi, rating));
        }
        return ret;
    }

    @RequestMapping(value = "/allTypes", method = RequestMethod.GET)
    public List<Menu_Item_Type> getAllTypes() {
        return menuItemTypeRepository.findAll();
    }

    @RequestMapping(value = "/getByName/{name}")
    public Menu_Item getByName(@PathVariable String name) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        Restaurant res = (Restaurant) attr.getAttribute("activeRestaurant", ServletRequestAttributes.SCOPE_SESSION);
        return menuItemRepository.findByNameAndRestname(name,res.getName());
    }


    @RequestMapping(value = "/exists", method = RequestMethod.PUT)
    public Menu_Item exists(@RequestBody Menu_Item menu_item) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        Restaurant res = (Restaurant) attr.getAttribute("activeRestaurant", ServletRequestAttributes.SCOPE_SESSION);
        Menu_Item ret = menuItemRepository.findByNameAndRestname(menu_item.getName(),res.getName());
        return ret;
    }

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public Menu_Item create(@RequestBody Menu_Item newMenu_Item) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        Restaurant res = (Restaurant) attr.getAttribute("activeRestaurant", ServletRequestAttributes.SCOPE_SESSION);
        newMenu_Item.setRestname(res.getName());
        return menuItemRepository.save(newMenu_Item);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.PUT)
    public void remove(@RequestBody Menu_Item toDelete) {
        menuItemRepository.delete(toDelete);
    }

    private double getMenuItemRating(String name) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        Restaurant res = (Restaurant) attr.getAttribute("activeRestaurant", ServletRequestAttributes.SCOPE_SESSION);
        List<Menu_Item_Rating> ratings = mirRepository.findByMenuItemNameAndRestaurantName(name,res.getName());
        ArrayList<Menu_Item_Rating> rts = new ArrayList<>(ratings);
        double rating = 0;
        for (Menu_Item_Rating rtg : rts) {
            rating += rtg.getRating();
        }
        if (rts.size() != 0) {
            rating /= rts.size();
        }
        return rating;


    }
}

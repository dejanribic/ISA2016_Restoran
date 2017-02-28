package com.desha.Controllers;

import com.desha.Beans.MenuItemWithRating;
import com.desha.Beans.Menu_Item;
import com.desha.Beans.Menu_Item_Type;
import com.desha.Repositories.Menu_ItemRepository;
import com.desha.Repositories.Menu_Item_TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @Autowired
    public Menu_ItemController(Menu_ItemRepository menuItemRepository,  Menu_Item_TypeRepository menuItemTypeRepository) {
        this.menuItemRepository = menuItemRepository;
        this.menuItemTypeRepository = menuItemTypeRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<MenuItemWithRating> getAll() {
        ArrayList<MenuItemWithRating> ret = new ArrayList<>();
        List<Menu_Item> mil =  menuItemRepository.findAll();
        return null;
    }

    @RequestMapping(value = "/allTypes", method = RequestMethod.GET)
    public List<Menu_Item_Type> getAllTypes() { return menuItemTypeRepository.findAll(); }

    @RequestMapping(value = "/getByName/{name}")
    public Menu_Item getByName(@PathVariable String name) {
        return menuItemRepository.findByName(name);
    }


    @RequestMapping(value = "/exists", method = RequestMethod.PUT)
    public Menu_Item exists(@RequestBody Menu_Item menu_item) {
        Menu_Item ret = menuItemRepository.findByName(menu_item.getName());
        return ret;
    }

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public Menu_Item create(@RequestBody Menu_Item newMenu_Item) {
       return menuItemRepository.save(newMenu_Item);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.PUT)
    public void remove(@RequestBody Menu_Item toDelete) {
        menuItemRepository.delete(toDelete);
    }
}

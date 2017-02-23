package com.desha.Controllers;

import com.desha.Beans.Menu_Item;
import com.desha.Repositories.Menu_ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Nenad on 2/22/2017.
 */
@RestController
@RequestMapping(value = "/menu-item")
public class Menu_ItemController {
    private Menu_ItemRepository repository;

    @Autowired
    public Menu_ItemController(Menu_ItemRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Menu_Item> getAll() { return repository.findAll(); }

    @RequestMapping(value = "/getByName/{name}")
    public Menu_Item getByName(@PathVariable String name) {
        return repository.findByName(name);
    }


    @RequestMapping(value = "/exists", method = RequestMethod.PUT)
    public Menu_Item exists(@RequestBody Menu_Item menu_item) {
        Menu_Item ret = repository.findByName(menu_item.getName());
        return ret;
    }

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public Menu_Item create(@RequestBody Menu_Item newMenu_Item) {
       return repository.save(newMenu_Item);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.PUT)
    public void remove(@RequestBody Menu_Item toDelete) {
        repository.delete(toDelete);
    }
}

package com.desha.Controllers;

import com.desha.Beans.Guest;
import com.desha.Beans.Manager;
import com.desha.Beans.Supplier;
import com.desha.Beans.Sys_Manager;
import com.desha.Repositories.GuestRepository;
import com.desha.Repositories.ManagerRepository;
import com.desha.Repositories.SupplierRepository;
import com.desha.Repositories.Sys_ManagerRepository;
import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(value = "/users")
public class UserController {

    private GuestRepository guests;
    private Sys_ManagerRepository sys_managers;
    private ManagerRepository managers;
    private SupplierRepository suppliers;

    @Autowired
    public UserController(GuestRepository guests, Sys_ManagerRepository sys_managers, ManagerRepository managers, SupplierRepository suppliers) {
        this.sys_managers = sys_managers;
        this.suppliers = suppliers;
        this.managers = managers;
        this.guests = guests;
    }

    @RequestMapping(value = "/allUsers", method = RequestMethod.GET)
    public List<Guest> getAll() {

        return guests.findAll();
    }

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public void create(@RequestBody Guest newGuest) {

        guests.save(newGuest);

        //String contentString = "<h2>Hello, welcome to the \"ISA 2016\" Restaurant app! \n \n Please confirm your  = = = email address by clicking on the following link:</h2>\n\n<a href=\"localhost:3000/users/confirmUser/" + newGuest.getEmail() + "\">Click me!</a>\n\n";
        String contentString = "Hello, welcome to the \"ISA 2016\" Restaurant app! \n \n Please confirm your email address by clicking on the following link:\n\nhttp://localhost:3000/users/confirmUser/" + newGuest.getEmail() + "\n\n Thanks!";
        //String contentString = "Hello, welcome to the \"ISA 2016\" Restaurant app! \n \n Please confirm your email address by clicking on the following link:\n\nhttps://www.w3schools.com\n\n Thanks!";


        String SendGridApiKey = "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";
        Content content = new Content("text/html", contentString);
        //Content content = new Content("text/plain", contentString);

        Email from = new Email("ISA.DAEMON@ISA2016.BRT");
        Email to = new Email(newGuest.getEmail());

        String subject = "ISA 2016 - User confirmation";

        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(SendGridApiKey);
        Request request = new Request();
        try {
            request.method = Method.POST;
            request.endpoint = "mail/send";
            request.body = mail.build();
            Response response = sg.api(request);
            //System.out.println(response.statusCode);
            //System.out.println(response.body);
            //System.out.println(response.headers);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @RequestMapping(value = "/confirmUser/{email:.+}")
    public String confirmUser(@PathVariable String email) {
        Guest guest = guests.findByEmail(email);
        guest.setConfirmed(1);
        guests.save(guest);
        return "<h1>You have succesfully confirmed your account.</h1><h2><a href=\"http://localhost:3000\">Go back!</a></h2>";
    }

    @RequestMapping(value = "/exists", method = RequestMethod.PUT)
    public Guest exists(@RequestBody Guest sentUser) {
        Guest guest = guests.findByEmailAndPassword(sentUser.getEmail(), sentUser.getPassword());
        Manager manager = managers.findByEmailAndPassword(sentUser.getEmail(), sentUser.getPassword());
        Sys_Manager sys_manager = sys_managers.findByEmailAndPassword(sentUser.getEmail(), sentUser.getPassword());
        Supplier supplier = suppliers.findByEmailAndPassword(sentUser.getEmail(), sentUser.getPassword());

        //System.out.println(guest);
        //System.out.println(manager);
        //System.out.println(sys_manager);
        //System.out.println(supplier);

        if (guest != null) {
            System.out.println(guest);
            System.out.println(guest.getEmail());
            System.out.println(guest.getPassword());

            return guest;
        }

        if (manager != null) {
            Guest returnGuest = new Guest();
            returnGuest.setEmail(manager.getEmail());
            returnGuest.setPassword(manager.getPassword());

            System.out.println(returnGuest);
            System.out.println(returnGuest.getEmail());
            System.out.println(returnGuest.getPassword());

            return returnGuest;
        }

        if (supplier != null) {
            Guest returnGuest = new Guest();
            returnGuest.setEmail(supplier.getEmail());
            returnGuest.setPassword(supplier.getPassword());

            System.out.println(returnGuest);
            System.out.println(returnGuest.getEmail());
            System.out.println(returnGuest.getPassword());

            return returnGuest;

        }
        if (sys_manager != null) {
            Guest returnGuest = new Guest();
            returnGuest.setEmail(sys_manager.getEmail());
            returnGuest.setPassword(sys_manager.getPassword());

            System.out.println(returnGuest);
            System.out.println(returnGuest.getEmail());
            System.out.println(returnGuest.getPassword());

            return returnGuest;

        }
        return null;
    }
}

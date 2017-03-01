package com.desha.Controllers;

import com.desha.Beans.*;
import com.desha.Repositories.*;
import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private GuestRepository guests;
    private Sys_ManagerRepository sys_managers;
    private ManagerRepository managers;
    private SupplierRepository suppliers;
    private EmployeeRepository employees;
    private CanWorkWithRepository cwws;

    @Value("${sendGridAPIKey}")
    private String sendGridAPIKey;

    @Autowired
    public UserController(GuestRepository guests, Sys_ManagerRepository sys_managers, ManagerRepository managers, SupplierRepository suppliers, EmployeeRepository employees, CanWorkWithRepository cwws) {
        this.sys_managers = sys_managers;
        this.suppliers = suppliers;
        this.managers = managers;
        this.guests = guests;
        this.employees = employees;
        this.cwws = cwws;
    }

    @RequestMapping(value = "/allUsers", method = RequestMethod.GET)
    public List<Guest> getAll() {
        return guests.findAll();
    }

    @RequestMapping(value = "/getOne/{email:.+}")
    public Guest getById(@PathVariable String email) {
        return guests.findByEmail(email);
    }

    @RequestMapping(value = "/getOneEmp/{email:.+}")
    public Employee getOneEmp(@PathVariable String email) {
        return employees.findByEmail(email);
    }

    @RequestMapping(value = "/changeEmp", method = RequestMethod.PUT)
    public void change(@RequestBody Employee emp) {
        employees.save(emp);
    }

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public void create(@RequestBody Guest newGuest) {

        // Cuvanje usera
        guests.save(newGuest);

        // Slanje mail-a:

        // Regular EMAIL
        String contentString = "Hello, welcome to the \"ISA 2016\" Restaurant app! \n \n Please confirm your email address by clicking on the following link:\n\nhttp://localhost:3000/users/confirmUser/" + newGuest.getEmail() + "\n\n Thanks!";

        // HTML EMAIL
        // String contentString = "<h2>Hello, welcome to the \"ISA 2016\" Restaurant app! \n \n Please confirm your  = = = email address by clicking on the following link:</h2>\n\n<a href=\"localhost:3000/users/confirmUser/" + newGuest.getEmail() + "\">Click me!</a>\n\n";

        Content content = new Content("text/html", contentString);
        //Content content = new Content("text/plain", contentString);

        Email from = new Email("ISA.DAEMON@ISA2016.BRT");
        Email to = new Email(newGuest.getEmail());

        String subject = "ISA 2016 - User confirmation";

        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(sendGridAPIKey);
        Request request = new Request();

        if (newGuest.getEmail().equals("dejanribic021@gmail.com")) {
            try {
                request.method = Method.POST;
                request.endpoint = "mail/send";
                request.body = mail.build();
                Response response = sg.api(request);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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
    public UserLogin exists(@RequestBody UserLogin sentUser) {

        Guest guest = guests.findByEmailAndPassword(sentUser.getEmail(), sentUser.getPassword());
        Manager manager = managers.findByEmailAndPassword(sentUser.getEmail(), sentUser.getPassword());
        Sys_Manager sys_manager = sys_managers.findByEmailAndPassword(sentUser.getEmail(), sentUser.getPassword());
        Supplier supplier = suppliers.findByEmailAndPassword(sentUser.getEmail(), sentUser.getPassword());
        Employee employee = employees.findByEmailAndPassword(sentUser.getEmail(), sentUser.getPassword());

        Can_Work_With CWW = cwws.findByEmail(sentUser.getEmail());

        UserLogin returnGuest = new UserLogin();


        // User Types:
        // Guest = 0
        // Manager = 1
        // Supplier = 2
        // Sys_Manager = 3
        // Employee = 4

        if (guest != null) {
            returnGuest.setEmail(guest.getEmail());
            returnGuest.setName(guest.getName());
            returnGuest.setPassword(guest.getPassword());
            returnGuest.setType(1);
        }

        if (manager != null) {
            returnGuest.setEmail(manager.getEmail());
            returnGuest.setPassword(manager.getPassword());
            returnGuest.setType(2);
        }

        if (supplier != null) {
            returnGuest.setEmail(supplier.getEmail());
            returnGuest.setPassword(supplier.getPassword());
            returnGuest.setType(3);
        }
        if (sys_manager != null) {
            returnGuest.setEmail(sys_manager.getEmail());
            returnGuest.setPassword(sys_manager.getPassword());
            returnGuest.setType(4);
        }
        if (employee != null) {
            returnGuest.setEmail(employee.getEmail());
            returnGuest.setPassword(employee.getPassword());
            returnGuest.setType(5);
            returnGuest.setEtype("nesto");
            returnGuest.setRestname(employee.getRestaurantName());
            returnGuest.setEtype("konobar");
            returnGuest.setFirstlog(employee.getFirstlog());
            returnGuest.setName(employee.getName());


            try {
                if (CWW.getMenu_item_type_name().equals("pice")) {
                    returnGuest.setEtype("pice");
                    System.out.println("ja sam sanker");
                } else if (CWW.getMenu_item_type_name().equals("meso")) {
                    returnGuest.setEtype("meso");
                    System.out.println("ja sam pecenje");
                } else if (CWW.getMenu_item_type_name().equals("salata")) {
                    returnGuest.setEtype("salata");
                    System.out.println("ja sam kuvar_za_salate");
                } else if (CWW.getMenu_item_type_name().equals("riba")) {
                    returnGuest.setEtype("riba");
                    System.out.println("ja sam kuvar_za_ribu");
                } else if (CWW.getMenu_item_type_name().equals("supa")) {
                    returnGuest.setEtype("supa");
                    System.out.println("ja sam kuvar_za_kuvano_jelo");
                } else {
                    returnGuest.setEtype("konobar");
                    System.out.println("ja sam konobar");
                }
            } catch (NullPointerException e) {
            }

        }


        // Employee

        // Postavi usera na session
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        attr.setAttribute("user", returnGuest, ServletRequestAttributes.SCOPE_SESSION);

        /* DIAGNOSTICS:
        System.out.println(returnGuest);
        System.out.println(returnGuest.getEmail());
        System.out.println(returnGuest.getPassword());
        System.out.println(returnGuest.getType());
        */

        return returnGuest;
    }
}

package com.desha.Controllers;

import com.desha.Beans.*;
import com.desha.Repositories.Restaurant_has_SupplierRepository;
import com.desha.Repositories.SupplierRepository;
import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.util.List;

/**
 * Created by Nenad on 2/22/2017.
 */
@RestController
@RequestMapping(value = "/supplier")
public class SupplierController {

    private SupplierRepository supplierRepository;
    private Restaurant_has_SupplierRepository restaurant_has_SupplierRepository;

    @Value("${sendGridAPIKey}")
    private String sendGridAPIKey;

    @Autowired
    public SupplierController(SupplierRepository supplierRepository, Restaurant_has_SupplierRepository restaurant_has_SupplierRepository) {
        this.supplierRepository = supplierRepository;
        this.restaurant_has_SupplierRepository = restaurant_has_SupplierRepository;
    }


    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public ReturnMessage create(@RequestBody Restaurant_has_Supplier newSupplier) {
        ReturnMessage ret = new ReturnMessage("Error");
        Supplier sup = new Supplier(newSupplier.getSupplierEmail(), "", "");
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        Restaurant res = (Restaurant) attr.getAttribute("activeRestaurant", ServletRequestAttributes.SCOPE_SESSION);
        newSupplier.setRestaurantName(res.getName());

        if (supplierRepository.findByEmail(newSupplier.getSupplierEmail()) == null) {
            supplierRepository.save(sup);
            restaurant_has_SupplierRepository.save(newSupplier);
            ret.setMessage("Supplier successfully registered");
            // TODO send mail
            sendMail(sup.getEmail());
            return ret;
        } else if (restaurant_has_SupplierRepository.exists(new Restaurant_has_SupplierKey(newSupplier.getRestaurantName(), newSupplier.getSupplierEmail()))) {
            ret.setMessage("Supplier already registered");
            return ret;
        }

        if (restaurant_has_SupplierRepository.save(newSupplier) != null) {
            ret.setMessage("Supplier successfully registered");
            return ret;
        }
        return ret;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Supplier getSupplier() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        UserLogin user = (UserLogin) attr.getAttribute("user", ServletRequestAttributes.SCOPE_SESSION);
        Supplier ret = supplierRepository.findByEmail(user.getEmail());
        ret.setPassword("");
        return ret;
    }

    @RequestMapping(value = "/setName/{name}", method = RequestMethod.PUT)
    public boolean setName(@PathVariable String name) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        UserLogin user = (UserLogin) attr.getAttribute("user", ServletRequestAttributes.SCOPE_SESSION);
        Supplier ret = supplierRepository.getOne(user.getEmail());
        ret.setName(name);
        supplierRepository.save(ret);
        return true;
    }

    @RequestMapping(value = "/setPassword", method = RequestMethod.PUT)
    public ReturnMessage setPassword(@RequestBody Password pass) {
        ReturnMessage ret = new ReturnMessage();
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        UserLogin user = (UserLogin) attr.getAttribute("user", ServletRequestAttributes.SCOPE_SESSION);
        Supplier newUser = supplierRepository.getOne(user.getEmail());
        if(newUser.getPassword().equals(pass.getOld())){
            newUser.setPassword(pass.getNewr());
            supplierRepository.save(newUser);
            ret.setMessage("Password successfully changed.");
        }else{
            ret.setMessage("Wrong old password.");
        }

        return ret;
    }
    private void sendMail(String email){
        //TODO change localhost:8080 to domain
        String contentString = "Hello, welcome to the \"ISA 2016\" Restaurant app! \n \n Please confirm your email address by clicking on the following link:\n\nhttp://localhost:8080/dkjasdHHHasldkeeeeads/" + email + "\n\n Thanks!";

        Content content = new Content("text/html", contentString);
        //Content content = new Content("text/plain", contentString);

        Email from = new Email("ISA.DAEMON@ISA2016.BRT");
        Email to = new Email(email);

        String subject = "ISA 2016 - User confirmation";

        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(sendGridAPIKey);
        Request request = new Request();
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

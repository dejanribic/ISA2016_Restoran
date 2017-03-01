package com.desha.Controllers;

import com.desha.Beans.*;
import com.desha.Repositories.DemandRepository;
import com.desha.Repositories.OfferRepository;
import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.persistence.LockModeType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nenad on 2/24/2017.
 */
@RestController
@RequestMapping(value = "/offer")
public class OfferController {
    private OfferRepository offerRepository;
    private DemandRepository demandRepository;

    @Value("${sendGridAPIKey}")
    private String sendGridAPIKey;

    @Autowired
    public OfferController(OfferRepository offerRepository, DemandRepository demandRepository) {
        this.offerRepository = offerRepository;
        this.demandRepository = demandRepository;
    }

    @RequestMapping(value = "/getByDemand", method = RequestMethod.PUT)
    public List<Offer> getByDemand(@RequestBody Demand demand) {
        return offerRepository.findByDemandNumAndRestaurantName(demand.getNum(), demand.getRestaurantName());
    }

    @RequestMapping(value = "/getBySupplier", method = RequestMethod.GET)
    public List<OfferForSupplier> getBySupplier() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        UserLogin user = (UserLogin) attr.getAttribute("user", ServletRequestAttributes.SCOPE_SESSION);
        ArrayList<OfferForSupplier> ret = new ArrayList<>();
        if (user.getType() == 3) {
            List<Offer> x = offerRepository.findBySupplierEmail(user.getEmail());
            ArrayList<Offer> xx = new ArrayList<>(x);
            for (Offer o : xx) {
                ret.add(new OfferForSupplier(o, demandRepository.findOne(new DemandKey(o.getDemandNum(), o.getRestaurantName()))));
            }
            return ret;
        }
        return null;
    }


    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public ReturnMessage create(@RequestBody Offer newOffer) {
        ReturnMessage ret = new ReturnMessage();
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        UserLogin user = (UserLogin) attr.getAttribute("user", ServletRequestAttributes.SCOPE_SESSION);
        newOffer.setSupplierEmail(user.getEmail());
        Demand d = demandRepository.findOne(new DemandKey(newOffer.getDemandNum(),newOffer.getRestaurantName()));
        if(d.isClosed()){
            ret.setMessage("Demand is already closed");
            return ret;
        }

        offerRepository.save(newOffer);
        return ret;
    }
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @RequestMapping(value = "/accept", method = RequestMethod.PUT)
    public void acceptOffer(@RequestBody Offer offer) {
        Demand demand = demandRepository.findOne(new DemandKey(offer.getDemandNum(), offer.getRestaurantName()));
        demand.setClosed(true);
        offer.setAccepted(true);

        offerRepository.save(offer);
        demandRepository.save(demand);
        sendMail(offer.getSupplierEmail(), "Your offer has been accepted. ISA RESTAURANT");
        ArrayList<Offer> offers = offerRepository.findByDemandNumAndRestaurantName(offer.getDemandNum(), offer.getRestaurantName());
        for (Offer o : offers) {
            if (!o.getSupplierEmail().equals(offer.getSupplierEmail())) {
                sendMail(o.getSupplierEmail(), "Your offer has been declined. ISA RESTAURANT");
            }
        }


    }

    private void sendMail(String email, String den) {
        String contentString = den;

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

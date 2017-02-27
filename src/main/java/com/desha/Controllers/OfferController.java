package com.desha.Controllers;

import com.desha.Beans.*;
import com.desha.Repositories.DemandRepository;
import com.desha.Repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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

    @Autowired
    public OfferController(OfferRepository offerRepository,DemandRepository demandRepository ){
        this.offerRepository = offerRepository;
        this.demandRepository = demandRepository;
    }

    @RequestMapping(value = "/getByDemand", method = RequestMethod.PUT)
    public List<Offer> getByDemand(@RequestBody Demand demand) {
        return offerRepository.findByDemandNumAndRestaurantName(demand.getNum(),demand.getRestaurantName());
    }

    @RequestMapping(value = "/getBySupplier", method = RequestMethod.GET)
    public List<OfferForSupplier> getBySupplier() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        UserLogin user = (UserLogin) attr.getAttribute("user", ServletRequestAttributes.SCOPE_SESSION);
        ArrayList<OfferForSupplier> ret = new ArrayList<>();
        if(user.getType() == 3) {
            List<Offer> x = offerRepository.findBySupplierEmail(user.getEmail());
            ArrayList<Offer> xx = new ArrayList<>(x);
            for(Offer o: xx){
                ret.add(new OfferForSupplier(o,demandRepository.findOne(new DemandKey(o.getDemandNum(),o.getRestaurantName()))));
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
        offerRepository.save(newOffer);
        return ret;
    }
    @RequestMapping(value = "/accept",method = RequestMethod.PUT)
    public void acceptOffer(@RequestBody Offer offer){
        Demand demand = demandRepository.findOne(new DemandKey(offer.getDemandNum(),offer.getRestaurantName()));
        // TODO posalji notifikaciju
        offer.setAccepted(true);
        demand.setClosed(true);
        offerRepository.save(offer);
        demandRepository.save(demand);

    }


}

package com.desha.Beans;

/**
 * Created by Nenad on 2/27/2017.
 */
public class OfferForSupplier {
    private Offer offer;
    private Demand demand;

    public OfferForSupplier() {
    }

    public OfferForSupplier(Offer offer, Demand demand) {
        this.offer = offer;
        this.demand = demand;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Demand getDemand() {
        return demand;
    }

    public void setDemand(Demand demand) {
        this.demand = demand;
    }
}
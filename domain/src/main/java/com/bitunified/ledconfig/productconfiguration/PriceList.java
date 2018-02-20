package com.bitunified.ledconfig.productconfiguration;


import com.bitunified.ledconfig.productconfiguration.price.PriceCount;

import java.util.ArrayList;
import java.util.List;

public class PriceList {

    private List<PriceCount> prices = new ArrayList<PriceCount>();

    private double totalPrice;

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<PriceCount> getPrices() {
        return prices;
    }

    public void setPrices(List<PriceCount> prices) {
        this.prices = prices;
    }
}

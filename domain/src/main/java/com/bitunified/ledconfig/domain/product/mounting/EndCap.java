package com.bitunified.ledconfig.domain.product.mounting;

import com.bitunified.ledconfig.domain.modeltypes.RealModel;


public class EndCap extends RealModel implements Comparable {


    @Override
    public int compareTo(Object o) {
        return o.hashCode() - this.hashCode();
    }

}

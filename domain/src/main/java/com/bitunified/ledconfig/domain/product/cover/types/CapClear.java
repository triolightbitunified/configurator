package com.bitunified.ledconfig.domain.product.cover.types;

import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.product.cover.Covering;

public class CapClear extends Cap {

    public CapClear(){
        this(new Dimension());
    }
    public CapClear(Dimension dimension) {
        super(dimension);
        TRANSLUCENCY.setValue("CLEAR");
    }
}

package com.bitunified.ledconfig.domain.product.PCB.types;

import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Property;
import com.bitunified.ledconfig.domain.product.PCB.LedStrip;


public class PowerLedStrip extends LedStrip {


    public PowerLedStrip() {
        super();
    }

    public PowerLedStrip(Dimension dimension) {
        super(dimension);
        addProperty(COLOR);

    }
}

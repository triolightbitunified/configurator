package com.bitunified.ledconfig.domain.product.PCB.types;


import com.bitunified.ledconfig.domain.Dimension;

public class TunnableLedStrip extends PowerLedStrip {

    public TunnableLedStrip(){
        super();
        this.setImageUrl("liniLEDTW.jpg");
    }
    public TunnableLedStrip(Dimension dimension){
        super(dimension);

    }
}

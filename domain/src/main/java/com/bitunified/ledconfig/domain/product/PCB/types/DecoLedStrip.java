package com.bitunified.ledconfig.domain.product.PCB.types;

import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Property;
import com.bitunified.ledconfig.domain.product.PCB.LedStrip;


public class DecoLedStrip extends LedStrip {

    public DecoLedStrip(){
        super();

    }
    public DecoLedStrip(Dimension dimension){
        super(dimension);
        this.setProductPage("http://www.liniled.com/products/?parent_id=25022&second_id=25926&third_id=25928");

    }
}

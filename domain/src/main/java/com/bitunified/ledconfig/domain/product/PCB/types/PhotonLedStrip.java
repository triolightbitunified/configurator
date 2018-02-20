package com.bitunified.ledconfig.domain.product.PCB.types;

import com.bitunified.ledconfig.domain.Dimension;


public class PhotonLedStrip extends PowerLedStrip {

    public PhotonLedStrip(){

        super();
        this.setImageUrl("liniLED-PCB-Photon.jpg");
    }
    public PhotonLedStrip(Dimension dimension){
        super(dimension);

    }
}

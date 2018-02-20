package com.bitunified.ledconfig.domain.product.accessoires;


import com.bitunified.ledconfig.domain.modeltypes.RealModel;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({CableChannel.class,Clip.class})
public class Accessoire extends RealModel {

    public Accessoire(){
        super();
        setStep(9);
    }
}

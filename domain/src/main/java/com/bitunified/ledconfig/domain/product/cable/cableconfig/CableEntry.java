package com.bitunified.ledconfig.domain.product.cable.cableconfig;

import com.bitunified.ledconfig.domain.modeltypes.ConfigurationModel;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({LeftViaEndCapCableEntry.class,LeftViaBottomCableEntry.class,LeftViaSideCableEntry.class,RightViaSideCableEntry.class,CenterViaSideCableEntry.class})
public class CableEntry extends ConfigurationModel {

    public CableEntry(){
        this.setStep(5);
    }
}


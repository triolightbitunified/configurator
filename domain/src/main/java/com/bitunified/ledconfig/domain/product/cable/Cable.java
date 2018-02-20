package com.bitunified.ledconfig.domain.product.cable;


import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.Property;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;
import com.bitunified.ledconfig.domain.product.PCB.types.*;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "typeClass")
public class Cable extends RealModel {

    public Cable(){
        setStep(4);
    }
    public static final String CABLE_TYPE_S = "cable_type";
    public static final String CABLE_END_S = "cable_end";

    public static final Property CABLE_TYPE=new Property(CABLE_TYPE_S,"");
    public static final Property CABLE_END=new Property(CABLE_END_S,"");
    public Cable(Dimension dimension) {
        super(dimension);
        setStep(4);
        addProperty(CABLE_TYPE);
        addProperty(CABLE_END);
    }
}

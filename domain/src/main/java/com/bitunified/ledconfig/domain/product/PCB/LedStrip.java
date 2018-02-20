package com.bitunified.ledconfig.domain.product.PCB;


import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Property;
import com.bitunified.ledconfig.domain.modeltypes.ConfigurationModel;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;
import com.bitunified.ledconfig.domain.product.PCB.types.*;
import com.bitunified.ledconfig.domain.product.cover.types.Cap;
import com.bitunified.ledconfig.domain.product.cover.types.Resin;
import com.bitunified.ledconfig.domain.product.profile.Profile;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

@XmlSeeAlso({HighPowerLedStrip.class, PhotonLedStrip.class, DecoLedStrip.class, PowerLedStrip.class, RGBLedStrip.class})
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "typeClass")
@JsonSubTypes({
        @JsonSubTypes.Type(value = HighPowerLedStrip.class),
        @JsonSubTypes.Type(value = DecoLedStrip.class),
        @JsonSubTypes.Type(value = PowerLedStrip.class),
        @JsonSubTypes.Type(value = RGBLedStrip.class),
        @JsonSubTypes.Type(value = PhotonLedStrip.class)
})
public class LedStrip extends RealModel {

    public static final String COLOR_TYPE = "color";
    public static final Property COLOR=new Property(COLOR_TYPE,"");
    public static final String KELVIN_TYPE = "kelvin";
    public static final int STEP = 3;
    @XmlTransient
    public final Property KELVIN=new Property(KELVIN_TYPE,"");

    public LedStrip(){
        this.setStep(STEP);
    }

    public static final String SECTION_WIDTH = "section";

    @XmlTransient
    public final Property SECTION=new Property(SECTION_WIDTH,null);

    public LedStrip(Dimension dimension) {
        super(dimension);
        this.setStep(STEP);
        addProperty(SECTION);
        addProperty(KELVIN);
        addProperty(COLOR);
    }
}

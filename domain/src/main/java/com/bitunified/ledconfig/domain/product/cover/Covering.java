package com.bitunified.ledconfig.domain.product.cover;


import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Property;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;
import com.bitunified.ledconfig.domain.product.cover.types.Cap;
import com.bitunified.ledconfig.domain.product.cover.types.Resin;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({Resin.class,Cap.class})
public class Covering extends RealModel{
    public static final String TRANSLUCENCY_S = "translucency";
    public static final Property TRANSLUCENCY = new Property(TRANSLUCENCY_S,"");
    public static final int CODE_LENGTH = 1;

    public Covering(){
        this(new Dimension());
    }

    public Covering(Dimension dimension){
        super(dimension);
        addProperty(TRANSLUCENCY);
        this.setStep(2);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

package com.bitunified.ledconfig.domain.product.cover.types;


import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Property;
import com.bitunified.ledconfig.domain.product.cover.Covering;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({ResinClear.class,ResinDiffuse.class})
public class Resin extends Covering {

    public Resin(){
        this(new Dimension());
    }
    public Resin(Dimension dimension) {
        super(dimension);

    }
}

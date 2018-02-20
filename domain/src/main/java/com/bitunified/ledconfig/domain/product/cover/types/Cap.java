package com.bitunified.ledconfig.domain.product.cover.types;

import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.product.cover.Covering;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({CapClear.class,CapDiffuse.class})
public class Cap extends Covering {

    public Cap(){
        this(new Dimension());
    }
    public Cap(Dimension dimension) {
        super(dimension);
    }
}

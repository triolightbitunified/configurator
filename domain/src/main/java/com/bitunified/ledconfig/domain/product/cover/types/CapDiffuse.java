package com.bitunified.ledconfig.domain.product.cover.types;

import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.product.cover.Covering;

public class CapDiffuse extends Cap {

    public CapDiffuse(){
        this(new Dimension());
    }
    public CapDiffuse(Dimension dimension) {
        super(dimension);
        TRANSLUCENCY.setValue("DIFFUSE");
    }
}

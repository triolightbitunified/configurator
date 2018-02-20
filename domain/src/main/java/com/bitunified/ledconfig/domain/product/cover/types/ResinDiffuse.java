package com.bitunified.ledconfig.domain.product.cover.types;


import com.bitunified.ledconfig.domain.Dimension;

public class ResinDiffuse extends Resin {

    public ResinDiffuse(){
        this(new Dimension());
    }

    public ResinDiffuse(Dimension dimension) {
        super(dimension);
        TRANSLUCENCY.setValue("DIFFUSE");
    }
}

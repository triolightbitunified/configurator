package com.bitunified.ledconfig.domain.product.mounting;


import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "typeClass")
public class EndCapBothSidesMounting extends Mounting {

    public EndCapBothSidesMounting() {
        super();
    }

}

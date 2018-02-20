package com.bitunified.ledconfig.composedproduct;

import com.bitunified.ledconfig.domain.modeltypes.RealModel;
import com.bitunified.ledconfig.domain.product.ModelResult;
import com.bitunified.ledconfig.parts.Part;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlMixed;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ComposedProduct extends RealModel {

    public ComposedProduct() {
        this.setStep(8);
        this.setName("Composed product");
    }


    public ComposedProduct(Integer totalWidth, Integer totalHeight) {
        this();
        this.getDimension().setWidth(totalWidth);
        this.getDimension().setHeight(totalHeight);

    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}

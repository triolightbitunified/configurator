package com.bitunified.ledconfig.domain.modeltypes;


import com.bitunified.ledconfig.composedproduct.ComposedProduct;
import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Margin;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.Relation;
import com.bitunified.ledconfig.domain.product.PCB.LedStrip;
import com.bitunified.ledconfig.domain.product.accessoires.Accessoire;
import com.bitunified.ledconfig.domain.product.cable.Cable;
import com.bitunified.ledconfig.domain.product.cable.cableconfig.CableEntry;
import com.bitunified.ledconfig.domain.product.cover.Covering;
import com.bitunified.ledconfig.domain.product.mounting.EndCap;
import com.bitunified.ledconfig.domain.product.mounting.Mounting;
import com.bitunified.ledconfig.domain.product.profile.Profile;
import com.bitunified.ledconfig.parts.Relatable;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({Profile.class, LedStrip.class, Cable.class, ComposedProduct.class, Accessoire.class, EndCap.class, Relatable.class,Covering.class})
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "typeClass")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Profile.class),
        @JsonSubTypes.Type(value = LedStrip.class),
        @JsonSubTypes.Type(value = Cable.class),
        @JsonSubTypes.Type(value = Accessoire.class),
        @JsonSubTypes.Type(value = EndCap.class),
        @JsonSubTypes.Type(value = ComposedProduct.class),
        @JsonSubTypes.Type(value = Covering.class)
})
public class RealModel extends Model {
    private Dimension dimension = new Dimension(null, null);

    private Dimension maxDimension=new Dimension();

    private Margin margin = new Margin();
    private String productPage;

    public RealModel() {

    }

    public RealModel(Dimension dimension) {
        this.dimension = dimension;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public Dimension getMaxDimension() {
        return maxDimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public void setMaxDimension(Dimension maxDimension) {
        this.maxDimension = maxDimension;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }




    public Margin getMargin() {
        return margin;
    }

    public void setMargin(Margin margin) {
        this.margin = margin;
    }


    public void setProductPage(String productPage) {
        this.productPage = productPage;
    }

    public String getProductPage() {
        return productPage;
    }
}

package com.bitunified.ledconfig.domain.modeltypes;

import com.bitunified.ledconfig.domain.Margin;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.product.PCB.LedStrip;
import com.bitunified.ledconfig.domain.product.cable.Cable;
import com.bitunified.ledconfig.domain.product.cable.cableconfig.CableEntry;
import com.bitunified.ledconfig.domain.product.cover.types.Cap;
import com.bitunified.ledconfig.domain.product.cover.types.Resin;
import com.bitunified.ledconfig.domain.product.mounting.Mounting;
import com.bitunified.ledconfig.domain.product.profile.Profile;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({CableEntry.class, Mounting.class})
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "typeClass")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CableEntry.class),
        @JsonSubTypes.Type(value = Mounting.class)
})
public class ConfigurationModel extends Model {


    private Margin margins=new Margin();

    public Margin getMargins() {
        return margins;
    }

    public void setMargins(Margin margins) {
        this.margins = margins;
    }
}

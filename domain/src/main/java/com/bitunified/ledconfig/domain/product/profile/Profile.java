package com.bitunified.ledconfig.domain.product.profile;


import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Property;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;
import com.bitunified.ledconfig.parts.Part;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


public class Profile extends RealModel {

    public static final int CODE_LENGTH = 1;
    private Dimension lengthForCasting=new Dimension();
    private String lengthForCastingStr="";
    public Profile(){

    }
    public Profile(Dimension dimension) {
        super(dimension);
        this.setStep(1);
    }


    public void setLengthForCasting(Dimension lengthForCasting) {
        this.lengthForCasting = lengthForCasting;
    }

    public Dimension getLengthForCasting() {
        return lengthForCasting;
    }

    public String getLengthForCastingStr() {
        return lengthForCastingStr;
    }

    public void setLengthForCastingStr(String lengthForCastingStr) {
        this.lengthForCastingStr = lengthForCastingStr;
    }


}

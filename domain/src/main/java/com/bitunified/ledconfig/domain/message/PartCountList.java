package com.bitunified.ledconfig.domain.message;

import java.util.ArrayList;
import java.util.List;


public class PartCountList {


    private List<PartCount> partList=new ArrayList<PartCount>();


    public List<PartCount> getPartList() {
        return partList;
    }

    public void setPartList(List<PartCount> partList) {
        this.partList = partList;
    }
}

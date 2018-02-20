package com.bitunified.ledconfig.domain.message;


import com.bitunified.ledconfig.parts.Part;

public class PartCount {
    private Part part;
    private Double count;
    public PartCount(){

    }
    public PartCount(Part part,Double count){
        this.part=part;
        this.count=count;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }
}

package com.bitunified.ledconfig.domain;


import java.io.Serializable;

public class Dimension implements Serializable{

    public Dimension(){

    }
    private Integer width;


    private Integer height;
    private Integer depth;


    public Unit unit;

    public Dimension(Integer width, Integer height,Integer depth) {
        this.width=width;
        this.height=height;
        this.depth=depth;

    }

    public Dimension(Integer width, Integer height) {
        this.width=width;
        this.height=height;
    }
    public Dimension(Integer width) {
        this.width=width;

    }

    @Override
    public String toString() {
        return "[Width:" + (width!=null?width:"")+" Height:"+(height!=null?height:"")+"]";
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getDepth() {

        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}

package com.bitunified.ledconfig.domain;


import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Margin implements Serializable {

    public Margin(int left,int right){
        this.left=left;
        this.right=right;
    }
    public Margin(int left,Object o){
        this.left=left;
    }
    public Margin(Object o,int right){
        this.right=right;
    }
    public Margin(){

    }


    private Integer left;

    private Integer right;

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;

    }

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }

    public void setLeftStr(String leftStr) {

        this.left=Integer.parseInt(leftStr);
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}

package com.bitunified.ledconfig.domain.message;


public enum MessageStatus {
    ERROR("red"), UNKNOWN("black"), WARNING("orange"),INFO("yellow");
    private final String color;

     MessageStatus(String color){
        this.color=color;
    }
}

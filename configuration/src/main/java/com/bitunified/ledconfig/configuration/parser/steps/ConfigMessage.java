package com.bitunified.ledconfig.configuration.parser.steps;


public class ConfigMessage {
    private final String messageText;

    private Integer step;
    public ConfigMessage(String message,Integer step){
        this.messageText=message;
        this.step=step;
    }

    public String getMessageText() {
        return messageText;
    }

    public Integer getStep() {
        return step;
    }
}

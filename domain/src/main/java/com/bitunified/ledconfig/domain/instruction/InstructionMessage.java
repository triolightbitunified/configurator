package com.bitunified.ledconfig.domain.instruction;


public class InstructionMessage {

    private String message;

    public InstructionMessage(){

    }
    public InstructionMessage(String text){
        this.message=text;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

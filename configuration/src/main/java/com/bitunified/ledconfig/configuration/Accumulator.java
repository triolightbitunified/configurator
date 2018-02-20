package com.bitunified.ledconfig.configuration;


public class Accumulator {

    int startPosition=0;
    public Accumulator(int startPositionCode) {
        this.startPosition=startPositionCode;
    }

    public Integer plus(int codeLength) {

        int newPosition= startPosition+codeLength;
        startPosition=newPosition;
        return newPosition;
    }

    public Integer start() {
        return startPosition;
    }
}

package com.bitunified.server.models;

import com.bitunified.ledconfig.parts.Part;

public class PartResult {
    private Part part;
    private String result;

    public PartResult(Part part, String result) {
        this.part = part;
        this.result = result;
    }

    public Part getPart() {
        return part;
    }

    public String getResult() {
        return result;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

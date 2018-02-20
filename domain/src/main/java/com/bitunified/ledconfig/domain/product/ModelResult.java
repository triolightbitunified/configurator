package com.bitunified.ledconfig.domain.product;


import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.parts.Part;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ModelResult {

    private String errorMessage="";
    private Model model;
    private Part part;
    public ModelResult(String errorMessageLength) {
        this.errorMessage = errorMessageLength;
    }
    public ModelResult(Model model) {
        this.model = model;
        errorMessage = null;
    }
    public ModelResult(Model model, Part part) {
        this.model = model;
        this.part=part;
    }
    public ModelResult(Model model, Part part, String errorMessageLength) {
        this.model = model;
        this.part=part;
        errorMessage = errorMessageLength;
    }
    public ModelResult(Model model, String errorMessageLength) {
        this.model = model;
        errorMessage = errorMessageLength;
    }


    public Model getModel() {
        return model;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

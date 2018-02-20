package com.bitunified.ledconfig.productconfiguration;


import java.util.ArrayList;
import java.util.List;

public class ProductConfiguration {

    private List<ModelChosenStep> modelsForSteps = new ArrayList<ModelChosenStep>();

    public List<ModelChosenStep> getModelsForSteps() {
        return modelsForSteps;
    }

    public void setModelsForSteps(List<ModelChosenStep> modelsForSteps) {
        this.modelsForSteps = modelsForSteps;
    }
}

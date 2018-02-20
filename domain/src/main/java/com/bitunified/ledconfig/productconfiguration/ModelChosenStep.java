package com.bitunified.ledconfig.productconfiguration;


import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.productconfiguration.steps.Step;


public class ModelChosenStep {


    private Step step;
    private Model chosenModel;
    private int modelValue;
    private boolean skipped;

    public int getModelValue() {
        return modelValue;
    }

    public void setModelValue(int modelValue) {
        this.modelValue = modelValue;
    }

    public Model getChosenModel() {
        return chosenModel;
    }

    public void setChosenModel(Model chosenModel) {
        this.chosenModel = chosenModel;
    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    public boolean isSkipped() {
        return skipped;
    }

    public void setSkipped(boolean skipped) {
        this.skipped = skipped;
    }
}

package com.bitunified.ledconfig.productconfiguration.steps;

import com.bitunified.ledconfig.domain.Model;

import java.util.List;

public class Step {
    private String description;
    private Integer stepindex;
    private StepType type;
    private List<Model> models;

    private boolean skippable = false;
    private Integer modelValue;
    private String configDescription;

    public Step() {

    }

    public Step(Integer stepindex, String description, List<Model> models, StepType type, boolean skippable, String configDescription) {
        this.description = description;
        this.models = models;
        this.type = type;
        this.stepindex = stepindex;
        this.skippable = skippable;
        this.configDescription = configDescription;
    }

    public Step(Integer stepindex, String description, List<Model> models, String configDescription) {
        this.description = description;
        this.models = models;
        this.type = StepType.VALUES;
        this.stepindex = stepindex;
        this.configDescription = configDescription;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StepType getType() {
        return type;
    }

    public void setType(StepType type) {
        this.type = type;
    }

    public Integer getStepindex() {
        return stepindex;
    }

    public void setStepindex(Integer stepindex) {
        this.stepindex = stepindex;
    }

    public Integer getModelValue() {
        return modelValue;
    }

    public void setModelValue(Integer modelValue) {
        this.modelValue = modelValue;
    }

    public boolean isSkippable() {
        return skippable;
    }

    public void setSkippable(boolean skippable) {
        this.skippable = skippable;
    }

    public String getConfigDescription() {
        return configDescription;
    }

    public void setConfigDescription(String configDescription) {
        this.configDescription = configDescription;
    }
}

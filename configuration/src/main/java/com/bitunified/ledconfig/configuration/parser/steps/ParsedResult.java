package com.bitunified.ledconfig.configuration.parser.steps;


import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.parts.Part;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParsedResult {

    private Set<Model> models=new HashSet<Model>();
    private List<ConfigMessage> messages=new ArrayList<ConfigMessage>();

    private Set<Part> parts = new HashSet<Part>();
    private List<ParseStep> steps;

    public List<ConfigMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ConfigMessage> messages) {
        this.messages = messages;
    }

    public Set<Model> getModels() {
        return models;
    }

    public void setModels(Set<Model> models) {
        this.models = models;
    }

    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }

    public void setSteps(List<ParseStep> steps) {
        this.steps = steps;
    }

    public List<ParseStep> getSteps() {
        return steps;
    }
}

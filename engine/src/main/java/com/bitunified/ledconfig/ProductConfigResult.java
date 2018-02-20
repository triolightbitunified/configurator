package com.bitunified.ledconfig;


import com.bitunified.ledconfig.configuration.parser.steps.ParseStep;
import com.bitunified.ledconfig.domain.instruction.InstructionMessage;
import com.bitunified.ledconfig.domain.message.Message;
import com.bitunified.ledconfig.parts.Part;
import org.drools.core.impl.StatefulKnowledgeSessionImpl;

import java.util.*;

public class ProductConfigResult {
    private Set<Message> messages=new HashSet<>();
    private Map<Integer,List<Message>> messageMap=new HashMap<>();
    private List models=new ArrayList<StatefulKnowledgeSessionImpl.ObjectStoreWrapper>();
    private Map<Part,Double> partList;
    private List<InstructionMessage> instructions;
    private List<ParseStep> parseSteps;
    public ProductConfigResult(List<ParseStep> steps, Set<Message> messages, Map<Integer, List<Message>> messageMap, Collection<?> objects, Map<Part, Double> partList, List<InstructionMessage> instructions) {
        this.messages=messages;
        this.messageMap=messageMap;
        this.models.addAll(objects);
        this.partList=partList;
        this.instructions=instructions;
        this.parseSteps=steps;
    }


    public List getModels() {
        return models;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public Map<Integer, List<Message>> getMessageMap() {
        return messageMap;
    }

    public Map<Part,Double> getPartList() {
        return partList;
    }

    public List<InstructionMessage> getInstructions() {
        return instructions;
    }

    public List<ParseStep> getParseSteps() {
        return parseSteps;
    }

    public void setParseSteps(List<ParseStep> parseSteps) {
        this.parseSteps = parseSteps;
    }
}

package com.bitunified.server.message;


import com.bitunified.ledconfig.configuration.parser.steps.ParseStep;
import com.bitunified.ledconfig.domain.instruction.InstructionMessage;
import com.bitunified.ledconfig.domain.message.Message;
import com.bitunified.ledconfig.domain.message.PartCount;
import com.bitunified.ledconfig.parts.Part;

import java.util.*;

public class ServerResponse {
    private String success;

    private String errorMessage;
    private Set<Message> messages=new HashSet<>();
    private Map<Integer, List<Message>> messageMap = new HashMap<>();

    private List<PartCount> partList=new ArrayList<PartCount>();

    private List<ParseStep> parseSteps;

    private List<InstructionMessage> instructions;

    private Double totalPrice;

    public ServerResponse(String success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }



    public Map<Integer, List<Message>> getMessageMap() {
        return messageMap;
    }

    public void setMessageMap(Map<Integer, List<Message>> messagesMap) {

//        for (Map.Entry message:messagesMap.entrySet()){
//            messageMap.put((Integer)message.getKey(),((Message)message.getValue()).getMessage());
//        }
        this.messageMap = messagesMap;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }


    public List<PartCount> getPartList() {
        return partList;
    }

    public void setPartList(List<PartCount> partList) {
        this.partList = partList;
    }



    public List<InstructionMessage> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<InstructionMessage> instructions) {
        this.instructions = instructions;
    }

    public List<ParseStep> getParseSteps() {
        return parseSteps;
    }

    public void setParseSteps(List<ParseStep> parseSteps) {
        this.parseSteps = parseSteps;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }
}

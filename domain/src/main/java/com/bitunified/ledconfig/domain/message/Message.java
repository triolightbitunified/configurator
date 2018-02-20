package com.bitunified.ledconfig.domain.message;


import com.bitunified.ledconfig.domain.Model;

public class Message {
    private Model model;
    private Integer step=null;

    private String message;

    private MessageStatus status=MessageStatus.UNKNOWN;

    public Message() {

    }
    public Message(String message, Integer step, MessageStatus status,Model model) {
        this.message = message;
        this.status = status;
        this.step=step;
        this.model=model;
    }
    public Message(String message, Integer step, MessageStatus status) {
        this.message = message;
        this.status = status;
        this.step=step;
    }
    public Message(String message, MessageStatus status) {
        this.message = message;
        this.status = status;
    }

    public Message(String message) {
        this.message = message;
    }
    public Message(Model model) {
        this.model = model;
    }
    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public MessageStatus getStatus() {
        return this.status;
    }

    public void setStatus(final MessageStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message1 = (Message) o;

        if (step != null ? !step.equals(message1.step) : message1.step != null) return false;
        if (!message.equals(message1.message)) return false;
        return status == message1.status;

    }

    @Override
    public int hashCode() {
        int result = step != null ? step.hashCode() : 0;
        result = 31 * result + message.hashCode();
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message: " + this.message + " - Status: " + status;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}

package com.example.apt.Clint;

public class bfcp_received_attribute {
    private int type;
    private int mandatoryBit;
    private int length;
    private int position;
    private int valid;
    private bfcp_received_attribute next;

    // Constructors, getters, and setters

    public bfcp_received_attribute() {
        // Default constructor
    }

    public bfcp_received_attribute(int type, int mandatoryBit, int length, int position, int valid, bfcp_received_attribute next) {
        this.type = type;
        this.mandatoryBit = mandatoryBit;
        this.length = length;
        this.position = position;
        this.valid = valid;
        this.next = next;
    }

    // Getters and setters

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMandatoryBit() {
        return mandatoryBit;
    }

    public void setMandatoryBit(int mandatoryBit) {
        this.mandatoryBit = mandatoryBit;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public bfcp_received_attribute getNext() {
        return next;
    }

    public void setNext(bfcp_received_attribute next) {
        this.next = next;
    }
}

package com.example.apt.Clint;

public class BFCPReceivedAttribute {
    private int type;
    private int mandatoryBit;
    private int length;
    private int position;
    private int valid;
    private BFCPReceivedAttribute next;

    // Constructors, getters, and setters

    public BFCPReceivedAttribute() {
        // Default constructor
    }

    public BFCPReceivedAttribute(int type, int mandatoryBit, int length, int position, int valid, BFCPReceivedAttribute next) {
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

    public BFCPReceivedAttribute getNext() {
        return next;
    }

    public void setNext(BFCPReceivedAttribute next) {
        this.next = next;
    }
}

package com.example.apt.Clint;

public class BFCPReceivedMessageError {
    private int attribute;
    private int code;
    private BFCPReceivedMessageError next;

    // Constructors, getters, and setters

    public BFCPReceivedMessageError() {
        // Default constructor
    }

    public BFCPReceivedMessageError(int attribute, int code, BFCPReceivedMessageError next) {
        this.attribute = attribute;
        this.code = code;
        this.next = next;
    }

    // Getters and setters

    public int getAttribute() {
        return attribute;
    }

    public void setAttribute(int attribute) {
        this.attribute = attribute;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public BFCPReceivedMessageError getNext() {
        return next;
    }

    public void setNext(BFCPReceivedMessageError next) {
        this.next = next;
    }
}


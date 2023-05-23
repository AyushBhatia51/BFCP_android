package com.example.apt.Clint;

public class bfcp_received_message_error {
    private int attribute;
    private int code;
    private bfcp_received_message_error next;

    // Constructors, getters, and setters

    public bfcp_received_message_error() {
        // Default constructor
    }

    public bfcp_received_message_error(int attribute, int code, bfcp_received_message_error next) {
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

    public bfcp_received_message_error getNext() {
        return next;
    }

    public void setNext(bfcp_received_message_error next) {
        this.next = next;
    }
}


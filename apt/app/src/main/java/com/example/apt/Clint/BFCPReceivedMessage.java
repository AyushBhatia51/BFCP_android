package com.example.apt.Clint;

import com.example.apt.JNIbfcp.bfcp_entity;

public class BFCPReceivedMessage {
    private bfcp_arguments arguments;
    private int version;
    private int reserved;
    private int primitive;
    private int length;
    private bfcp_entity entity;
    private BFCPReceivedAttribute firstAttribute;
    private BFCPReceivedMessageError errors;

    // Constructors, getters, and setters

    public BFCPReceivedMessage() {
        // Default constructor
    }

    public BFCPReceivedMessage(bfcp_arguments arguments, int version, int reserved, int primitive,
                               int length, bfcp_entity entity, BFCPReceivedAttribute firstAttribute,
                               BFCPReceivedMessageError errors) {
        this.arguments = arguments;
        this.version = version;
        this.reserved = reserved;
        this.primitive = primitive;
        this.length = length;
        this.entity = entity;
        this.firstAttribute = firstAttribute;
        this.errors = errors;
    }

    // Getters and setters

    public bfcp_arguments getArguments() {
        return arguments;
    }

    public void setArguments(bfcp_arguments arguments) {
        this.arguments = arguments;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getReserved() {
        return reserved;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }

    public int getPrimitive() {
        return primitive;
    }

    public void setPrimitive(int primitive) {
        this.primitive = primitive;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public bfcp_entity getEntity() {
        return entity;
    }

    public void setEntity(bfcp_entity entity) {
        this.entity = entity;
    }

    public BFCPReceivedAttribute getFirstAttribute() {
        return firstAttribute;
    }

    public void setFirstAttribute(BFCPReceivedAttribute firstAttribute) {
        this.firstAttribute = firstAttribute;
    }

    public BFCPReceivedMessageError getErrors() {
        return errors;
    }

    public void setErrors(BFCPReceivedMessageError errors) {
        this.errors = errors;
    }
}

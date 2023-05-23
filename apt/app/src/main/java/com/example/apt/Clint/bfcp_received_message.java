package com.example.apt.Clint;

import com.example.apt.JNIbfcp.bfcp_entity;

public class bfcp_received_message {
    private bfcp_arguments arguments;
    private int version;
    private int reserved;
    private int primitive;
    private int length;
    private bfcp_entity entity;
    private bfcp_received_attribute first_attribute;
    private bfcp_received_message_error errors;

    // Constructors, getters, and setters

    public bfcp_received_message() {
        // Default constructor
    }

    public bfcp_received_message(bfcp_arguments arguments, int version, int reserved, int primitive,
                                 int length, bfcp_entity entity, bfcp_received_attribute firstAttribute,
                                 bfcp_received_message_error errors) {
        this.arguments = arguments;
        this.version = version;
        this.reserved = reserved;
        this.primitive = primitive;
        this.length = length;
        this.entity = entity;
        this.first_attribute = firstAttribute;
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

    public bfcp_received_attribute getFirstAttribute() {
        return first_attribute;
    }

    public void setFirstAttribute(bfcp_received_attribute firstAttribute) {
        this.first_attribute = firstAttribute;
    }

    public bfcp_received_message_error getErrors() {
        return errors;
    }

    public void setErrors(bfcp_received_message_error errors) {
        this.errors = errors;
    }
}

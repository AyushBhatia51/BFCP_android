package com.example.apt.Clint;

public class bfcp_supported_list {
    public short element;                     // Element (Attribute/Primitive)
    public bfcp_supported_list next;        // Pointer to next supported element instance

    public bfcp_supported_list(short element, bfcp_supported_list next) {
        this.element = element;
        this.next = next;
    }
}

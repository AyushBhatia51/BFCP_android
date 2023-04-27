package com.example.apt.JNIbfcp;

import com.example.apt.Clint.bfcp_arguments;

public class bfcp_received_message_handler {
    bfcp_arguments arguments;
    public int data;
    public int version;                          // The version of the received message
    public int reserved;                         // The reserved bits
    public int primitive;                        // The primitive of the message
    public int length;                           // The length of the message
    public bfcp_entity entity;                     // The entities of the message (IDs)
    public bfcp_received_attribute firstAttribute; // A list of all attributes in the message
    public bfcp_received_message_error errors;

}

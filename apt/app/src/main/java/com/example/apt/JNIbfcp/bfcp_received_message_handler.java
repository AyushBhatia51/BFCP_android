package com.example.apt.JNIbfcp;

import com.example.apt.Clint.bfcp_arguments;
import com.example.apt.Clint.bfcp_received_attribute;
import com.example.apt.Clint.bfcp_received_message_error;

public class bfcp_received_message_handler {
    bfcp_arguments arguments;
    public short data;
    public short version;                          // The version of the received message
    public short reserved;                         // The reserved bits
    public short primitive;                        // The primitive of the message
    public short length;                           // The length of the message
    public bfcp_entity entity;                     // The entities of the message (IDs)
    public bfcp_received_attribute firstAttribute; // A list of all attributes in the message
    public bfcp_received_message_error errors;

}

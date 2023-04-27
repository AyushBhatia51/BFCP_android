package com.example.apt.JNIbfcp;

public class bfcp_received_attribute {
    public int type;                // The attribute type
    public int mandatoryBit;        // The Mandatory Bit
    public int length;              // The length of the attribute
    public int position;            // Its position in the message buffer
    public int valid;               // If errors occur in parsing, the attribute is marked as not valid
    public bfcp_received_attribute next;
}

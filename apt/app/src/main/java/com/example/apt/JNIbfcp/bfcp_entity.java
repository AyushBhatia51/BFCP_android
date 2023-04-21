package com.example.apt.JNIbfcp;

public class bfcp_entity {

    public long conferenceID;
    public int transactionID;
    public int userID;
    public void sethello() {
        JNIbfcpclass.bfcp_build_message_Hello("");
    }
    public String gethello() {
        return  JNIbfcpclass.bfcp_build_message_Hello("asdfghjk");
    }
}

package com.example.apt.JNIbfcp;

public class bfcp_message {
    private String buffer;
    private int position;
    private int length;
    //MsgPtr
    private long msgPtr;

    private bfcp_entity entity;

    public bfcp_message() {
        entity = new bfcp_entity();
    }

    public long getMsgPtr() {
        return msgPtr;
    }

    public void setMsgPtr(long msgPtr) {
        this.msgPtr = msgPtr;
    }

    public String getBuffer() {
        return buffer;
    }

    public void setBuffer(String buffer) {
        this.buffer = buffer;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getConferenceID() {
        return entity.conferenceID;
    }

    public void setConferenceID(int conferenceID) {
        entity.conferenceID = conferenceID;
    }

    public int getTransactionID() {
        return entity.transactionID;
    }

    public void setTransactionID(int transactionID) {
        entity.transactionID = transactionID;
    }

    public int getUserID() {
        return entity.userID;
    }

    public void setUserID(int userID) {
        entity.userID = userID;
    }
}

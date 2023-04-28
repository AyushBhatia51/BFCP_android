package com.example.apt.JNIbfcp;

public class bfcp_message {
    private String buffer;
    private short position;
    private short length;
    //MsgPtr
    private long msgPtr;


    public long getMsgPtr() {
        return msgPtr;
    }

    public void setMsgPtr(long msgPtr) {
        this.msgPtr = msgPtr;
    }
    //MsgPtr

    public String getBuffer() {
        return buffer;
    }

    public void setBuffer(String buffer) {
        this.buffer = buffer;
    }

    public short getPosition() {
        return position;
    }

    public void setPosition(short position) {
        this.position = position;
    }

    public short getLength() {
        return length;
    }

    public void setLength(short length) {
        this.length = length;
    }
    //    public void sethello() {
//        //JNIbfcpclass.bfcp_build_message_Hello(this.bfcpEntity);
//    }
//    public bfcp_message gethello() {
//       // return  JNIbfcpclass.bfcp_build_message_Hello(this.bfcpEntity);
//        return null;
//    }

}
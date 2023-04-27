package com.example.apt.JNIbfcp;

public  class  bfcp_message {
//    public static String message;
    bfcp_entity bfcpEntity = new bfcp_entity();


    public void sethello() {
        JNIbfcpclass.bfcp_build_message_Hello(this.bfcpEntity);
    }
    public bfcp_message gethello() {
        return  JNIbfcpclass.bfcp_build_message_Hello(this.bfcpEntity);
    }

}
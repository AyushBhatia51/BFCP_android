package com.example.apt.JNIbfcp;


public class bfcp_entity {

    public int conferenceID ;
    public int transactionID ;
    public int userID ;
//    public void sethello() {
//        JNIbfcpclass.bfcp_build_message_Hello("");
//    }
//    public BFCPclient.bfcp_message gethello() {
//        return  JNIbfcpclass.bfcp_build_message_Hello("asdfghjk");
//    }
    public int getConferenceID(){
        return conferenceID;
    }
    public int getTransactionID(){
        return transactionID;
    }
    public int getUserID(){
        return userID;
    }
}

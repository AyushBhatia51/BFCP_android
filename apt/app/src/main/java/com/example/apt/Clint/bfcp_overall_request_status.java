package com.example.apt.Clint;

public class bfcp_overall_request_status {
    public short frqID;                   // OVERALL-REQUEST-STATUS-HEADER
    public bfcp_request_status rs;      // REQUEST-STATUS, optional
    public String sInfo;                // STATUS-INFO, optional

    public bfcp_overall_request_status(short frqID, bfcp_request_status rs, String sInfo) {
        this.frqID = frqID;
        this.rs = rs;
        this.sInfo = sInfo;
    }
}

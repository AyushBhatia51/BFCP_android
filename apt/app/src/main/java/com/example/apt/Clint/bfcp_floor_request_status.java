package com.example.apt.Clint;

public class bfcp_floor_request_status {
    public int fID;                      // FLOOR-REQUEST-STATUS-HEADER
    public bfcp_request_status rs;       // REQUEST-STATUS, optional
    public String sInfo;                 // STATUS-INFO, optional
    public bfcp_floor_request_status next; // pointer to next instance (to manage lists)

    public bfcp_floor_request_status(int fID, bfcp_request_status rs, String sInfo, bfcp_floor_request_status next) {
        this.fID = fID;
        this.rs = rs;
        this.sInfo = sInfo;
        this.next = next;
    }
}

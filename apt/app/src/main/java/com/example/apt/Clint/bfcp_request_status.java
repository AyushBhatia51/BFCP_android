package com.example.apt.Clint;

public class bfcp_request_status {
    public short rs;  // Request Status
    public short qp;  // Queue Position

    public bfcp_request_status(short rs, short qp) {
        this.rs = rs;
        this.qp = qp;
    }
}

package com.example.apt.Clint;

public class bfcp_request_status {
    public int rs;  // Request Status
    public int qp;  // Queue Position

    public bfcp_request_status(int rs, int qp) {
        this.rs = rs;
        this.qp = qp;
    }
}

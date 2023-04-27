package com.example.apt.Clint;

public class bfcp_floor_request_information {
    public int frqID;                                // FLOOR-REQUEST-INFORMATION-HEADER
    public bfcp_overall_request_status oRS;          // OVERALL-REQUEST-STATUS, optional
    public bfcp_floor_request_status fRS;            // FLOOR-REQUEST-STATUS list
    public bfcp_user_information beneficiary;        // BENEFICIARY-INFORMATION, optional
    public bfcp_user_information requested_by;       // REQUESTED-BY-INFORMATION, optional
    public int priority;                            // PRIORITY, optional
    public String pInfo;                            // PARTICIPANT-PROVIDED-INFO, optional
    public bfcp_floor_request_information next;      // pointer to next instance (to manage lists)

    public bfcp_floor_request_information(int frqID, bfcp_overall_request_status oRS, bfcp_floor_request_status fRS, bfcp_user_information beneficiary, bfcp_user_information requested_by, int priority, String pInfo, bfcp_floor_request_information next) {
        this.frqID = frqID;
        this.oRS = oRS;
        this.fRS = fRS;
        this.beneficiary = beneficiary;
        this.requested_by = requested_by;
        this.priority = priority;
        this.pInfo = pInfo;
        this.next = next;
    }
}

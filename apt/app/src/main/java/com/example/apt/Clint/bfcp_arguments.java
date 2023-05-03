package com.example.apt.Clint;

import com.example.apt.JNIbfcp.bfcp_entity;

public class bfcp_arguments {
        public int primitive;
        public bfcp_entity entity;
        public int fID;                             // Floor ID list
        public int frqID;                                     // Floor Request ID
        public int bID;                                       // Beneficiary ID
        public int priority;                                  // Priority
        public bfcp_floor_request_information frqInfo;             // Floor Request Information
        public bfcp_user_information beneficiary;                 // Beneficiary Information
        public bfcp_request_status rs;                            // Request Status
        public String pInfo;                                    // Participant Provided Info
        public String sInfo;                                    // Status Info
        public bfcp_error error;                                 // Error Code & Details
        public String eInfo;                                    // Error Info
        public bfcp_supported_list primitives;                    // Supported Primitives list
        public bfcp_supported_list attributes;                    // Supported Attributes list
        public int nonce;                                     // Nonce (currently UNUSED)
        public bfcp_digest digest;


}

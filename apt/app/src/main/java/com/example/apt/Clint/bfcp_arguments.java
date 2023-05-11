package com.example.apt.Clint;

import com.example.apt.JNIbfcp.bfcp_entity;

public class bfcp_arguments {
        public short primitive;
        public bfcp_entity entity;
        public 	bfcp_floor_id_list fID;			/* Floor ID list */
                                     // Floor ID list
        public short frqID;                                     // Floor Request ID
        public short bID;                                       // Beneficiary ID
        public short priority;                                  // Priority
        public bfcp_floor_request_information frqInfo;             // Floor Request Information
        public bfcp_user_information beneficiary;                 // Beneficiary Information
        public bfcp_request_status rs;                            // Request Status
        public char pInfo;                                    // Participant Provided Info
        public char sInfo;                                    // Status Info
        public bfcp_error error;                                 // Error Code & Details
        public char eInfo;                                    // Error Info
        public bfcp_supported_list primitives;                    // Supported Primitives list
        public bfcp_supported_list attributes;                    // Supported Attributes list
        public short nonce;                                     // Nonce (currently UNUSED)
        public bfcp_digest digest;


}

package com.example.apt.JNIbfcp;

    public class bfcp_received_message_error {
        public int attribute;                    // The attribute where the error happened
        public int code;                         // The Parsing-specific Error Code
        public bfcp_received_message_error next; // There could be more errors, it's a linked list

        public bfcp_received_message_error(int attribute, int code, bfcp_received_message_error next) {
            this.attribute = attribute;
            this.code = code;
            this.next = next;
        }
    }



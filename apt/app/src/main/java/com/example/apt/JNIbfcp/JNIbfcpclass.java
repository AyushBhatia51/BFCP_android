package com.example.apt.JNIbfcp;

import android.util.Log;

import com.example.apt.Clint.bfcp_supported_list;

public class JNIbfcpclass extends bfcp_entity{
    static {

        try {
            System.loadLibrary("bfcpmsg");
            Log.e("succccccccc", "load lib successfully");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Failed to load native library libbfcpmsg\n" + e);
            Log.e("failedffff", "asdf: ");
        }
    }


    public final native bfcp_message bfcp_build_message_Hello(bfcp_entity entity);
    //public final native bfcp_message bfcp_build_message_HelloAck(bfcp_entity entity, bfcp_supported_list primitives, bfcp_supported_list attributes);
    }


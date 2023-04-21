package com.example.apt.JNIbfcp;

import android.util.Log;

public class JNIbfcpclass extends bfcp_entity{
    static {

        try {
            System.loadLibrary("bfcpmsg");
            Log.e("succccccccc", "load lib successfully");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Failed to load native library libbfcpmsg\n" + e);
            Log.e("failedffff", "asdfjklsdl;l;: ");
        }
    }
    public final static native String bfcp_build_message_Hello(String str);

    }


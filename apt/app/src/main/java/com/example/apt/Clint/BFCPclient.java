package com.example.apt.Clint;

import android.util.Log;

import com.example.apt.JNIbfcp.bfcp_entity;
import com.example.apt.JNIbfcp.bfcp_participant_information;
import java.net.*;
import javax.net.ssl.*;
import java.io.*;
import java.util.concurrent.locks.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.net.ssl.SSLContext;

public class BFCPclient {

    public static Socket socket;
    public static  Object BFCP_SEND_CHECK_ERRORS = null;

    private static int BFCP_OVER_TCP = 1;
    private static int BFCP_OVER_TLS = 2;
    private static int BFCP_FCS_DEFAULT_PORT = 9876; // or some other value

    private static int bfcp_transport=0;
    private static SSLContext context;
    private static SSLSocket session;
    private static Socket server_sock;
    private static Thread thread;
    private Lock count_mutex = new ReentrantLock();

    private long conferenceID;
    private short userID;

    private static InetAddress serverAddress;
    private static int serverPort;

    public static int bfcp_hello_participant(bfcp_participant_information participant) {

//        if(participant == null) {
//            return -1;
//        }

        int error;
        bfcp_arguments arguments;
        bfcp_message message ;

//        pthread_mutex_lock(&count_mutex);

        /* Prepare a new 'Hello' message */
        arguments = bfcp_new_arguments();
        Object primitive;
//        arguments.primitive = null;
        Object entity;
        Object conferenceID;
        Object base_transactionID;
        Object userID;
//        Log.d("exception",String.valueOf(arguments.entity.conferenceID));
//        arguments.entity = (bfcp_entity) bfcp_new_entity(arguments.entity.conferenceID, arguments.entity.transactionID, arguments.entity.userID);
        message = bfcp_build_message(arguments);
        if(message == null) {
//            pthread_mutex_unlock(&count_mutex);
            return -1;
        }

//        pthread_mutex_unlock(&count_mutex);

        /* Send the message to the FCS */
        error = send_message_to_server(message, socket);
        System.out.println(error);
        BFCP_SEND_CHECK_ERRORS.toString();
        return error;
    }
    public static bfcp_message bfcp_build_message(bfcp_arguments arguments) {
        return null;
    }

    public static Object bfcp_new_entity(Object o, Object base_transactionID, Object o1) {
        return null;
    }
    public static  bfcp_arguments bfcp_new_arguments() {
        return null;
    }



    static int send_message_to_server(bfcp_message message, Socket socket) {
        try{
            connect();
            Log.d("yes", "connected");
        }catch (Exception e){
            Log.d("no",String.valueOf(e));

        }
        return 0;
    }
    public BFCPclient() {

        this.conferenceID = 1001;
        this.userID = 3001;
        try {
            this.serverAddress = InetAddress.getByName("192.168.8.138");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        this.serverPort =  2345;
    }


    public static void connect() throws IOException {
        bfcp_transport = BFCP_OVER_TCP; // or BFCP_OVER_TLS

        if(bfcp_transport == BFCP_OVER_TLS) {
            /* Initialize TLS-related stuff */
            try {
                context = SSLContext.getInstance("TLSv1.2");
                context.init(null, null, null);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        try {
            SocketAddress serverSocketAddress = new InetSocketAddress(serverAddress, serverPort);
            server_sock = new Socket();
            server_sock.connect(serverSocketAddress, 5000); // 5 second timeout
        }catch (Exception e){
            Log.d("exception",String.valueOf(e));
        }
        if(bfcp_transport == BFCP_OVER_TLS) {
            /* Once the connection has been established, make the SSL Handshake */
            try {
                session = (SSLSocket) context.getSocketFactory().createSocket(server_sock, serverAddress.getHostAddress(), serverPort, true);
                session.startHandshake();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }

        // handle incoming messages on a separate thread
        thread = new Thread(new Runnable() {
            public void run() {
                try {
                    InputStream input = session.getInputStream(); // or server_sock.getInputStream()
                    while (true) {
                        // read and handle incoming messages
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private class  bfcp_message {
//        public  String ss ="hello";
    }

    private class bfcp_arguments {
        public String primitive = "hello";
        public bfcp_entity entity;
    }
}

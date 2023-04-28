package com.example.apt.Clint;

import static android.system.Os.connect;

import android.util.Log;

import com.example.apt.JNIbfcp.JNIbfcpclass;
import com.example.apt.JNIbfcp.bfcp_entity;
import com.example.apt.JNIbfcp.bfcp_message;
import com.example.apt.JNIbfcp.bfcp_participant_information;
import com.example.apt.MainActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import javax.net.ssl.*;

import java.util.concurrent.locks.*;
import java.io.IOException;
import java.net.Socket;

import javax.net.ssl.SSLContext;

public class bfcp_client {
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
    public JNIbfcpclass myclass = new JNIbfcpclass();
    private long conferenceID;
    private short userID;

    private static InetAddress serverAddress;
    private static int serverPort;

    public int bfcp_hello_participant(bfcp_participant_information participant) {

//        if(participant == null) {
//            return -1;
//        }

        int error;
        bfcp_arguments arguments = new bfcp_arguments();
        bfcp_message message ;

//        pthread_mutex_lock(&count_mutex);
//
        /* Prepare a new 'Hello' message */

        arguments.primitive = "Hello";
//        arguments.primitive = null;
        bfcp_entity rrr = new bfcp_entity();
        rrr.conferenceID = 3001;
        rrr.transactionID = 0;
        rrr.userID = 1001;

        arguments.entity = rrr;
        Log.d("exception",String.valueOf(arguments.entity.conferenceID));
        message = bfcp_build_message(arguments);
        if(message == null) {
//            pthread_mutex_unlock(&count_mutex);
            return -1;
        }
//
//        pthread_mutex_unlock(&count_mutex);

        /* Send the message to the FCS */

        error = send_message_to_server(message);
        System.out.println(error);
        BFCP_SEND_CHECK_ERRORS.toString();
        return error;
    }
    public  bfcp_message bfcp_build_message(bfcp_arguments arguments) {
        if(arguments.primitive == "Hello"){
            return myclass.bfcp_build_message_Hello(arguments.entity);
            //return arguments;
        }
        else {
            return null;
        }
    }

    public static Object bfcp_new_entity(Object o, Object base_transactionID, Object o1) {
        return null;
    }
//    public static  bfcp_arguments
//    bfcp_new_arguments() {
//        return null;
//    }



   public static int send_message_to_server(bfcp_message message) {
       PrintWriter output;
       BufferedReader input;
       try{
            socket = new Socket("192.168.8.138", 2345);
            output = new PrintWriter(socket.getOutputStream());
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output.write(String.valueOf(message));
            output.flush();
        }catch (Exception e){
            Log.d("no",String.valueOf(e));

        }
        return 0;
    }
 bfcp_message bfcpMessage = new bfcp_message();
    



    public static void connect() throws IOException {
        bfcp_transport = BFCP_OVER_TCP; // or BFCP_OVER_TLS

        if (bfcp_transport == BFCP_OVER_TLS) {
            /* Initialize TLS-related stuff */
            try {
                context = SSLContext.getInstance("TLSv1.2");
                context.init(null, null, null);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }

//        try {
//            SocketAddress serverSocketAddress = new InetSocketAddress(serverAddress, serverPort);
//            server_sock = new Socket();
//            server_sock.connect(serverSocketAddress, 5000); // 5 second timeout
//        }catch (Exception e){
//            Log.d("exception",String.valueOf(e));
//        }
//        if(bfcp_transport == BFCP_OVER_TLS) {
//            /* Once the connection has been established, make the SSL Handshake */
//            try {
//                session = (SSLSocket) context.getSocketFactory().createSocket(server_sock, serverAddress.getHostAddress(), serverPort, true);
//                session.startHandshake();
//            } catch (Exception e) {
//                e.printStackTrace();
//                return;
//            }
//        }
//
//        // handle incoming messages on a separate thread
//        thread = new Thread(new Runnable() {
//            public void run() {
//                try {
//                    InputStream input = session.getInputStream(); // or server_sock.getInputStream()
//                    while (true) {
//                        // read and handle incoming messages
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        thread.start();
//    }


//       class bfcp_arguments {
//            public String primitive = "hello";
//            public bfcp_entity entity;
//        }
    }

//    private static class bfcp_arguments {
//
//    }
}

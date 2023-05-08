package com.example.apt.Clint;

import static android.system.Os.connect;


import android.annotation.SuppressLint;
import android.util.Log;

import com.example.apt.JNIbfcp.JNIbfcpclass;
import com.example.apt.JNIbfcp.bfcp_entity;
import com.example.apt.JNIbfcp.bfcp_message;
import com.example.apt.JNIbfcp.bfcp_participant_information;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import javax.net.ssl.*;

import java.nio.ByteBuffer;
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
   public static bfcp_message message;
   public bfcp_message msg;
   public bfcp_message getmsg;

    private static final String SERVER_IP = "192.168.8.138";
    private static final int SERVER_PORT = 2345;

    private OutputStream outputStream;
    private InputStream inputStream;

    public void BFCPClient() throws IOException {
        socket = new Socket(SERVER_IP, SERVER_PORT);
        outputStream = socket.getOutputStream();
        inputStream = socket.getInputStream();
    }
    public void close() throws IOException {
        socket.close();
    }
    public void sendMessage(bfcp_message message) throws IOException {
        byte[] buffer = message.getBuffer();
        outputStream.write(buffer);
        outputStream.flush();
    }
    public bfcp_message receiveMessage() throws IOException {
        byte[] headerBuffer = new byte[12];
        int readBytes = 0;
        while (readBytes < 12) {
            readBytes += inputStream.read(headerBuffer, readBytes, 12 - readBytes);
        }
        int payloadLength = ByteBuffer.wrap(headerBuffer, 8, 4).getInt();
        byte[] payloadBuffer = new byte[payloadLength - 12];
        readBytes = 0;
        while (readBytes < payloadBuffer.length) {
            readBytes += inputStream.read(payloadBuffer, readBytes, payloadBuffer.length - readBytes);
        }
        byte[] messageBuffer = new byte[payloadLength];
        System.arraycopy(headerBuffer, 0, messageBuffer, 0, 12);
        System.arraycopy(payloadBuffer, 0, messageBuffer, 12, payloadBuffer.length);
        return new bfcp_message(messageBuffer,new short[]{0,(short) messageBuffer.length});
    }
    @SuppressLint("SuspiciousIndentation")
    public int bfcp_hello_participant(bfcp_participant_information participant) throws IOException {
        int error;
        bfcp_arguments arguments = new bfcp_arguments();
        arguments.primitive = 11;
        bfcp_entity rrr = new bfcp_entity();
        rrr.conferenceID = 1001;
        rrr.transactionID = 0;
        rrr.userID = 3001;
        arguments.entity = rrr;
        arguments.fID = 2001;
        arguments.frqID = 0;
        arguments.bID = 0;
        arguments.priority = 0;
        arguments.frqInfo = null;
        arguments.beneficiary = null;
        arguments.rs = null;
        arguments.pInfo = null;
        arguments.sInfo = null;
        arguments.error = null;
        arguments.eInfo = null;
        arguments.primitives = null;
        arguments.attributes = null;
        arguments.nonce = 0;
        arguments.digest = null;
        Log.d("exception",String.valueOf(arguments.entity.conferenceID));
        message = bfcp_build_message(arguments);

        Log.d("Data_from_lib", String.valueOf(message.getPosition()));
        bfcp_client ff = new bfcp_client();
        ff.sendMessage(message);
        ff.getmsg = receiveMessage();
        Log.d("back",String.valueOf(getmsg.getLength()));

        if(message == null) {
//            pthread_mutex_unlock(&count_mutex);
            return -1;
        }
//        pthread_mutex_unlock(&count_mutex);
        /* Send the message to the FCS */
            error =1;
//            BFCP_SEND_CHECK_ERRORS.toString();

        return error;
    }
    public  bfcp_message bfcp_build_message(bfcp_arguments arguments) {
        if(arguments.primitive == 11){
            msg =  myclass.bfcp_build_message_Hello(arguments.entity);
            //return arguments;
            if (msg != null) {
                Log.d("DATA_lib", String.valueOf(msg.getPosition()));
               // msg.setMsgPtr(msg.getBuffer(), msg.getPosition(), msg.getLength());
            }
            return msg;
        }
        else {
            return null;
        }
    }

    /*public static int send_message_to_server(bfcp_message message) {

        new Thread(() -> {
            final String SERVER_IP = "192.168.8.138";
//                SERVER_PORT = Integer.parseInt(etPort.getText().toString().trim());
            final int SERVER_PORT = 2345;

            try {
                Log.d("helo","hello");
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                OutputStream outputStream = socket.getOutputStream();
                InputStream inputStream = socket.getInputStream();

                byte[] buffer = message.getBuffer();
                outputStream.write(buffer);
                Log.d("Socket connected","Success");
                outputStream.flush();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] responseBuffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(responseBuffer)) != -1){
                    baos.write(responseBuffer, 0,bytesRead);
                }
                byte[] responseBytes = baos.toByteArray();
                message.setBuffer(responseBytes);
                message.setMsgPtr(responseBytes, message.getPosition(), message.getLength());
                Log.d("data",String.valueOf(message.getPosition()));
                outputStream.close();
                inputStream.close();
                socket.close();
            }catch (Exception e){
                Log.d("exception",String.valueOf(e));
            }
        }).start();
        return 0;
    }*/
    public static Object bfcp_new_entity(Object o, Object base_transactionID, Object o1) {
        return null;
    }

}

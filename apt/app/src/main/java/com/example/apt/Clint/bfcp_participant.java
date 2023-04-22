//package com.example.apt.Clint;
//
//import static org.apache.http.conn.ssl.SSLSocketFactory.SSL;
//
//import android.net.http.SslError;
//
//import com.example.apt.JNIbfcp.bfcp_entity;
//import com.example.apt.JNIbfcp.bfcp_participant_information;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.net.Socket;
//import java.net.UnknownHostException;
//
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.SSLContextSpi;
//
//public class bfcp_participant {
//public int bfcp_transport ;
////public static SSL session;
//public int BFCP_OVER_TLS =1;
//public int BFCP_OVER_TCP =0;
//public static short base_transactionID =1;
//
//public static Socket socket;
//    public static  Object BFCP_SEND_CHECK_ERRORS = null;
//
//
//    public int bfcp_hello_participant(bfcp_participant_information participant) {
//
//        if(participant == null) {
//            return -1;
//        }
//
//        int error;
//        bfcp_arguments arguments;
//        bfcp_message message  = null;
//
////        pthread_mutex_lock(&count_mutex);
//
//        /* Prepare a new 'Hello' message */
//        arguments = bfcp_new_arguments();
//        Object primitive;
//        arguments.primitive = "Hello";
//        Object entity;
//        Object conferenceID;
//        Object base_transactionID;
//        Object userID;
//        arguments.entity = (bfcp_entity) bfcp_new_entity(arguments.entity.conferenceID, arguments.entity.transactionID, arguments.entity.userID);
//        message = bfcp_build_message(arguments);
//        if(message == null) {
////            pthread_mutex_unlock(&count_mutex);
//            return -1;
//        }
//
////        pthread_mutex_unlock(&count_mutex);
//
//        /* Send the message to the FCS */
//        error = send_message_to_server(message, socket);
//        BFCP_SEND_CHECK_ERRORS.toString();
//        return 0;
//    }
//
//    private Object bfcp_new_entity(Object o, Object base_transactionID, Object o1) {
//        return null;
//    }
//
//    private bfcp_arguments bfcp_new_arguments() {
//        return null;
//    }
//
//    private bfcp_message bfcp_build_message(bfcp_arguments arguments) {
//        return null;
//    }
//
//    public class bfcp_message {
//        String buffer;		/* The buffer containing the message */
//        short position;	/* The position indicator for the buffer */
//        short length;
//    }
//
//    private class bfcp_arguments {
//        public String primitive;
//        bfcp_entity entity;
//    }
//
//    int send_message_to_server(bfcp_message message, Socket socket){
//        if(message == null)
//            return -1;
//        int  byteleft = 0;
//        int error =0;
//        int total =0;
//        int bytesleft =0;
//        bytesleft =message.length;
//        Timeval timeval = new Timeval();
//        timeval.tv_sec=10;
//        timeval.tv_usec=0;
//
//        while (total < message.length){
//            if (bfcp_transport == BFCP_OVER_TLS) {
////                error = new SSL(session, message.buffer + total, byteleft);
//            }  else {
//                error = 2;
////            send(sockfd,(String)(message.buffer+total),byteleft,0);
//            }       if (error == -1){
//                break;
////                total += error;
////                bytesleft -= error;
//            }
//        }
//        try {
//             socket = new Socket("192.168.8.138", 2345); // connect to server
//            OutputStream out = socket.getOutputStream();
//            out.write("Hello, server!".getBytes()); // send message to server
//            out.flush();
//
//            InputStream in = socket.getInputStream();
//            byte[] buffer = new byte[1024];
//            int bytesRead = in.read(buffer); // read response from server
//            String response = new String(buffer, 0, bytesRead);
//            System.out.println("Server response: " + response);
//
//            socket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//
//        base_transactionID++;
//        return 0;
//    }
//
//    bfcp_message received_message_from_server(Socket socket, int close_socket){
//        int error, total =0;
//        bfcp_message message = null;
//        int in_length;
////        String common_header =(String)
//
//        String hostname = "192.168.8.138";
//        int port =  2345;
//
//        Thread gfgThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try (Socket socket = new Socket(hostname, port)) {
//
//                    InputStream input = socket.getInputStream();
//                    InputStreamReader reader = new InputStreamReader(input);
//
//                    int character;
//                    StringBuilder data = new StringBuilder();
//
//                    while ((character = reader.read()) != -1) {
//                        data.append((char) character);
//                    }
//
//                    System.out.println(data);
//
//
//                } catch (UnknownHostException ex) {
//
//                    System.out.println("Server not found: " + ex.getMessage());
//
//                } catch (IOException ex) {
//
//                    System.out.println("I/O error: " + ex.getMessage());
//                }
//                catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        gfgThread.start();
//
//       close_socket = 0;
//
//        return null;
//    }
//
//}

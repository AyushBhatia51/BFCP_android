package com.example.apt.Clint;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class SocketAsync extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "SocketAsync";
    private Socket socket;
    private final String serverIp;
    private final int serverPort;
    private InputStream inputStream;

    private MessageListener listener;
    public SocketAsync(String serverIp, int serverPort) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }
    public void setMessageListener(MessageListener listener) {
        this.listener = listener;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            socket = new Socket(serverIp, serverPort);

            Thread recvThread = new Thread(new ReceiveThread());
            recvThread.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void sendMessage(byte[] message) throws IOException {
        if (socket == null || socket.isClosed() || getStatus() != Status.FINISHED) {
            throw new IOException("Socket is not connected");
        }
        int total = 0;
        int bytesLeft = message.length;
        OutputStream outputStream = socket.getOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        //DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
        String utf8String = new String(message, StandardCharsets.UTF_8);
        //outputStream.writeInt(message.length);
        while (bytesLeft > 0) {
            bufferedOutputStream.write(message, total, bytesLeft);
            bufferedOutputStream.flush();
            total += bytesLeft;
            bytesLeft -= bytesLeft;
        }
        //outputStream.write(message);
       bufferedOutputStream.close();
        Log.d(TAG, "Sent message: " + new String(message));
       /* byte[] commonHeader = new byte[12];
        int bytesRead = inputStream.read(commonHeader);
        if (bytesRead == -1) {
            Log.d(TAG, "error in stream or end of stream ");
            // Handle end-of-stream condition or error
        } else if (bytesRead < 12) {
            Log.d(TAG, "Incomplete stream");
        } else {
            Log.d(TAG, "Success data");
            // Process the received data
            // ...
        }*/





    }
    public boolean isSocketConnected() {
        return socket != null && socket.isConnected();
    }
    private class ReceiveThread implements Runnable {
        @Override
        public void run() {
            try {
                byte[] commonHeader = new byte[12];
                
                inputStream = socket.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(inputStream);
               // DataInputStream is = new DataInputStream(inputStream);
                while (true) {
                   // int messageLength = bis.readInt();
                    //byte[] buffer = new byte[messageLength];
                   int bytesRead = bis.read(commonHeader);
                    if (bytesRead == -1) {
                        Log.d(TAG, "error in stream or end of stream ");
                        break;
                    }
                    else if (bytesRead < 12) {
                        Log.d(TAG, "Incomplete stream");
                    } else {
                        Log.d(TAG, "Success data");
                    }
                    /*if (listener != null) {
                        byte[] message = Arrays.copyOf(buffer, bytesRead);
                        listener.onMessageReceived(message);
                    }*/
                }
            } catch (RuntimeException | IOException e) {
                Log.e(TAG, "Error receiving message", e);
            }
        }
    }


    public void closeSocket() throws IOException {
        if (socket != null && !socket.isClosed()) {
            socket.close();
            socket = null;
        }
    }
    public interface MessageListener {
        void onMessageReceived(byte[] message);
    }
}
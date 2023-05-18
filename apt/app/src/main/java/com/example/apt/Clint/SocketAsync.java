package com.example.apt.Clint;

import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Arrays;

public class SocketAsync extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "SocketAsync";
    private Socket socket;
    private final String serverIp;
    private final int serverPort;

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

        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

        //outputStream.writeInt(message.length);
        outputStream.write(message);
        outputStream.flush();

        Log.d(TAG, "Sent message: " + new String(message));
    }
    public boolean isSocketConnected() {
        return socket != null && socket.isConnected();
    }
    private class ReceiveThread implements Runnable {
        @Override
        public void run() {
            try {
                InputStream inputStream = socket.getInputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    if (listener != null) {
                        byte[] message = Arrays.copyOfRange(buffer, 0, bytesRead);
                        listener.onMessageReceived(message);
                    }
                }
            } catch (IOException e) {
                Log.e(TAG, "Error receiving message", e);
            }
        }
    }
   /* public byte[] receiveMessage() throws IOException {
        if (socket == null || socket.isClosed()) {
            throw new IOException("Socket is not connected");
        }
        Log.d(TAG, String.valueOf("messageLength"));

        InputStream inputStream = socket.getInputStream();
        Log.d(TAG, String.valueOf(inputStream.available()));
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        //DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);

            if (inputStream.available() == 0) {
                break;
            }
        }
        byte[] message = byteArrayOutputStream.toByteArray();

        Log.d(TAG, "Received message: " + new String(message));

        return message;
    }*/

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
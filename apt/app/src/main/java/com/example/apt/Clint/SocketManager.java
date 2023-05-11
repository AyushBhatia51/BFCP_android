package com.example.apt.Clint;

import android.util.Log;

import java.io.IOException;

public class SocketManager implements SocketAsync.MessageListener{
    private SocketAsync socketAsync;
    private String serverIp;
    private int serverPort;
    private SocketAsync.MessageListener messageListener;

    public SocketManager(String serverIp, int serverPort) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.socketAsync = new SocketAsync(serverIp, serverPort);
        socketAsync.setMessageListener(this);
    }

    public void connect() {
        socketAsync.execute();
    }

    public void disconnect() throws IOException {
        socketAsync.closeSocket();
    }

    public boolean isConnected() {
        return socketAsync.isSocketConnected();
    }

    public void sendMessage(byte[] message) throws IOException {
        socketAsync.sendMessage(message);
    }

    public void setMessageListener(SocketAsync.MessageListener listener) {
        this.messageListener = listener;
    }
    @Override
    public void onMessageReceived(byte[] message) {
        Log.d("tag","messagerecieved");
    }
}

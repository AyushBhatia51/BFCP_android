package com.example.apt.Clint;

import java.io.IOException;

public class SocketManager {
    private SocketAsync socketAsync;
    private String serverIp;
    private int serverPort;

    public SocketManager(String serverIp, int serverPort) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.socketAsync = new SocketAsync(serverIp, serverPort);
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

    public byte[] receiveMessage() throws IOException {
        return socketAsync.receiveMessage();
    }
}

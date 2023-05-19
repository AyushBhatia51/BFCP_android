package com.example.apt.Clint;

import android.os.AsyncTask;
import android.util.Log;

import com.example.apt.JNIbfcp.bfcp_message;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class SocketAsync {
    private static final String TAG = "SocketAsync";
    private Socket socket;
    private BufferedOutputStream bos;
    private BufferedInputStream bis;
    private OutputStream oss;
    private InputStream iss;
    private final String serverIp;
    private final int serverPort;

    private SendMessageAsync sendMessageAsync;
    private MessageListener listener;

    public SocketAsync(String serverIp, int serverPort) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }

    public void setMessageListener(MessageListener listener) {
        this.listener = listener;
    }

    public void connect() {
        ConnectAsync connectAsync = new ConnectAsync();
        connectAsync.execute();
    }

    public boolean isSocketConnected() {
        return socket != null && socket.isConnected();
    }

    public void sendMessage(bfcp_message message) {
        if (sendMessageAsync == null || sendMessageAsync.getStatus() == AsyncTask.Status.FINISHED) {
            sendMessageAsync = new SendMessageAsync();
            sendMessageAsync.execute(message);
        } else {
            Log.e(TAG, "Previous send message task is still running");
        }
    }

    public void disconnect() {
        if (socket != null && !socket.isClosed()) {
            try {
                socket.close();
                socket = null;
            } catch (IOException e) {
                Log.e(TAG, "Error closing the socket", e);
            }
        }
    }

    private class ConnectAsync extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                socket = new Socket(serverIp, serverPort);
                oss = socket.getOutputStream();
                bos = new BufferedOutputStream(oss);

                iss= socket.getInputStream();
                bis = new BufferedInputStream(iss);
            } catch (IOException e) {
                Log.e(TAG, "Error connecting to the server", e);
            }
            return null;
        }
    }

    private class SendMessageAsync extends AsyncTask<bfcp_message, Void, Void> {
        @Override
        protected Void doInBackground(bfcp_message... messages) {
            try {
                bfcp_message message = messages[0];
                byte[] buffer = message.getBuffer();
                short length = message.getLength();
                oss.write(buffer, 0, length);
                oss.flush();
            }catch (IOException e){
                Log.d("io",String.valueOf(e));
            }
            try{
                byte[] commonHeaders = new byte[12];
                int bytesRead = iss.read(commonHeaders,0,12);

                if (bytesRead == 12) {
                    int messageLength = extractMessageLength(commonHeaders);
                    byte[] message = new byte[messageLength];
                    int totalBytesRead = 0;
                    while (totalBytesRead < messageLength) {
                        int remainingBytes = messageLength - totalBytesRead;
                        bytesRead = iss.read(message, totalBytesRead, remainingBytes);

                        if (bytesRead == -1) {
                            break;
                        }

                        totalBytesRead += bytesRead;
                    }
                    if (totalBytesRead == messageLength) {
                        Log.d("full message", "full message");
                        if (listener != null) {
                            listener.onMessageReceived(message);
                        }
                    } else {
                        Log.e(TAG, "Incomplete message received");
                    }
                } else {
                    Log.e(TAG, "Incomplete common headers received");
                }
            } catch (IOException e) {
                Log.e(TAG, "Error receiving message", e);
            }
            return null;
        }

        private int extractMessageLength(byte[] commonHeaders) {
            return ByteBuffer.wrap(commonHeaders, 8, 4).getInt();
        }
    }

    public interface MessageListener {
        void onMessageReceived(byte[] message);
    }
}

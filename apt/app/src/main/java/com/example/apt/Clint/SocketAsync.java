package com.example.apt.Clint;

import android.os.AsyncTask;
import android.util.Log;

import com.example.apt.JNIbfcp.JNIbfcpclass;
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
    public bfcp_message msg;
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
                int bytt = iss.read();
                Log.d("bytt",String.valueOf(bytt));
                int bytesRead = iss.read(commonHeaders,0,12);
                bfcp_message newmsg = new bfcp_message(commonHeaders,new short[] { (short) 0, (short) bytesRead });
                int in_length = bfcp_get_length(newmsg) + 12;
                int total = in_length - 12;
                byte[] buffer = new byte[total];
                int missing = total;
                while (true) {
                    if (missing <= 0) {
                        break;
                    }
                    bytesRead = iss.read(buffer, total - missing, missing);
                    if (bytesRead == -1) {
                        // There was an error while receiving the message
                        buffer = null;
                        break;
                    }
                    missing -= bytesRead;
                }
                if (total < 0) {
                    // The reported length is corrupted
                    commonHeaders = null;
                    Log.d(TAG, "Reported memory is corrupt");
                   // return null;
                }
                if (total == 0) {
                    // The whole message has already been received, there's no payload
                    commonHeaders = null;
                    Log.d(TAG, "Entire message received");
                   // return newmsg;
                }

                commonHeaders = Arrays.copyOf(commonHeaders, in_length);

                System.arraycopy(buffer, 0, commonHeaders, 12, total);

                bfcp_message message = new bfcp_message(commonHeaders, new short[] { (short) 0, (short) in_length });


                if (listener != null) {
                    listener.onMessageReceived(message);
                }
// Clean up allocated buffers
                buffer = null;
                commonHeaders = null;



            } catch (IOException e) {
                Log.e(TAG, "Error receiving message", e);
            }
            return null;
        }

        private int extractMessageLength(byte[] commonHeaders) {
            int packetlen = ByteBuffer.wrap(commonHeaders, 8, 4).getInt();
            Log.d(TAG, String.valueOf(packetlen));
            return packetlen;
        }

        private short bfcp_get_length(bfcp_message message) {
            if (message == null) {
                return 0;
            }
            int ch32;       // 32 bits
            short length;   // 16 bits
            byte[] buffer = message.getBuffer();
            ByteBuffer byteBuffer = ByteBuffer.wrap(buffer, 0, 4);
            ch32 = byteBuffer.getInt();
            ch32 = Integer.reverseBytes(ch32);
            length = (short) (ch32 & 0x0000FFFF);
            Log.d(TAG, String.valueOf(length));
            return length;
        }
    }

    public interface MessageListener {
        void onMessageReceived(bfcp_message message);

    }
}

package com.example.apt;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apt.Clint.SocketAsync;
import com.example.apt.Clint.SocketManager;
import com.example.apt.Clint.StreamInitializer;
import com.example.apt.Clint.bfcp_client;
import com.example.apt.JNIbfcp.*;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    Thread Thread1 = null;
    EditText etIP, etPort;
    TextView tvMessages;
    EditText etMessage;
    Button btnSend;
    String SERVER_IP ="192.168.8.138";
    bfcp_message message;
    Socket socket;
    int SERVER_PORT =2345;
    private SocketManager socketManager;
    private bfcp_message bfM;
    DataOutputStream dataOutputStream;
    ObjectOutputStream objout;
    int error;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        socketManager = new SocketManager(SERVER_IP, SERVER_PORT);
        Button btnConnect = findViewById(R.id.btnConnect);
        btnSend = findViewById(R.id.btnSend);
        socketManager.connect();
        bfcp_participant_information initialpart = new bfcp_participant_information();
        bfcp_received_message_handler bfcpReceivedMessageHandler = new bfcp_received_message_handler();
        initialpart.bfcp_initialize_bfcp_participant(3001, 1001, "192.168.8.138", 2345, bfcpReceivedMessageHandler, 0);
        Log.e("parti", String.valueOf(initialpart.getConferenceID()));
        bfcp_client newcli = new bfcp_client();
        btnConnect.setOnClickListener(v -> new Thread(() -> {
            while (!socketManager.isConnected()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Log.d("socket", String.valueOf(socketManager.isConnected()));
                bfM = newcli.bfcp_hello_participant(initialpart);
                socketManager.sendMessage(bfM.getBuffer());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start());
        btnSend.setOnClickListener(v -> new Thread(() -> {
            while (socketManager.isConnected()) {
                try {
                    byte[] buffer = socketManager.receiveMessage();
                    // bfcp_message message = new bfcp_message(buffer, new short[]{0, (short) buffer.length});
                    Log.d("rec11111", String.valueOf(buffer.length));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start());


        Log.d("error", "Error" + error);
        etIP = findViewById(R.id.etIP);
        etPort = findViewById(R.id.etPort);
        tvMessages = findViewById(R.id.tvMessages);
        etMessage = findViewById(R.id.etMessage);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Disconnect socket when activity is destroyed
        try {
            socketManager.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//public static bfcp_message received_message_from_server(){
//        bfcp_message message1 = null;
//    return message1;
//
//}
}


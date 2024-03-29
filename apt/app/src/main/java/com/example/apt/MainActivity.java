package com.example.apt;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apt.Clint.SocketAsync;
import com.example.apt.Clint.StreamInitializer;
import com.example.apt.Clint.bfcp_arguments;
import com.example.apt.Clint.bfcp_client;
import com.example.apt.Clint.bfcp_received_attribute;
import com.example.apt.Clint.bfcp_received_message;
import com.example.apt.Clint.bfcp_received_message_error;
import com.example.apt.JNIbfcp.*;
import android.annotation.SuppressLint;
import android.content.Intent;
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
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    Thread Thread1 = null;
    EditText etIP, etPort;
    TextView tvMessages;
    EditText etMessage;
    Button btnSend, parse;
    String SERVER_IP ="192.168.8.138";
    bfcp_message message;
    Socket socket;
    int SERVER_PORT =2345;
    private SocketAsync socketAsync;
    private bfcp_message bfM,brM;
    private bfcp_entity entity;
    private bfcp_received_attribute firstAttribute;
    private bfcp_received_message_error errors;
    private bfcp_arguments arguments;


    private bfcp_received_message recmsg;
    DataOutputStream dataOutputStream;
    ObjectOutputStream objout;
    int error;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* try {
            socketAsync = new SocketAsync(SERVER_IP, SERVER_PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        Button btnConnect = findViewById(R.id.btnConnect);
        btnSend = findViewById(R.id.btnSend);
        parse = findViewById(R.id.parse);
        //socketManager.connect();
        bfcp_participant_information initialpart = new bfcp_participant_information();
        bfcp_received_message_handler bfcpReceivedMessageHandler = new bfcp_received_message_handler();
        initialpart.bfcp_initialize_bfcp_participant(3001, 1001, "192.168.8.138", 2345, bfcpReceivedMessageHandler, 0);
        Log.e("parti", String.valueOf(initialpart.getConferenceID()));
        bfcp_client newcli = new bfcp_client();
        btnConnect.setOnClickListener(v -> {
            if (socketAsync == null || !socketAsync.isSocketConnected()) {
                socketAsync = new SocketAsync(SERVER_IP, SERVER_PORT);
                socketAsync.setMessageListener(message -> {
                    runOnUiThread(() -> {
                        bfM = message;
                        Log.d("byt",String.valueOf(message));
                        //JNIbfcpclass jnIbfcpclass = new JNIbfcpclass();

                    });
                });
                runOnUiThread(() -> {
                    socketAsync.connect();
                });
            } else {
                Log.e("Main", "Socket is already connected");
            }
        });



        btnSend.setOnClickListener(v -> {
            if (socketAsync != null && socketAsync.isSocketConnected()) {
                new Thread(() -> {
                    try {
                        bfM = newcli.bfcp_hello_participant(initialpart);
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                        objectOutputStream.writeObject(bfM);
                        objectOutputStream.flush();
                        Log.d("main",String.valueOf(bfM.getLength()));
                        // byte[] data = byteArrayOutputStream.toByteArray();
                        byte[] data = bfM.getBuffer();
                        runOnUiThread(() -> {
                            socketAsync.sendMessage(bfM);
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();

            } else {
                Log.e("main", "Socket is not connected");
            }
        });
//        bfcp_received_message brM = new bfcp_received_message();
//
//       brM.bfcp_received_message(arguments,12,0,12,12,entity,firstAttribute,errors);


         parse.setOnClickListener(v -> {
             recmsg = newcli.bfcp_parse_message(bfM);
             Log.d("main", String.valueOf(recmsg.getVersion()));
             Log.d("rek",String.valueOf(bfM));
         });
        /*
        btnConnect.setOnClickListener(v -> {
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
                });*/
        /*btnSend.setOnClickListener(v -> new Thread(() -> {
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
*/
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
        //socketAsync.closeSocket();
    }
//public static bfcp_message received_message_from_server(){
//        bfcp_message message1 = null;
//    return message1;
//
//}
}


package com.example.apt;

import androidx.appcompat.app.AppCompatActivity;
import com.example.apt.Clint.bfcp_client;
import com.example.apt.JNIbfcp.*;
import android.annotation.SuppressLint;
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
    private bfcp_message bfM;
    DataOutputStream dataOutputStream;
    ObjectOutputStream objout;
    int error;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bfcp_participant_information initialpart = new bfcp_participant_information();
        bfcp_received_message_handler bfcpReceivedMessageHandler = new bfcp_received_message_handler();
        initialpart.bfcp_initialize_bfcp_participant(3001, 1001, "192.168.8,138", 2345, bfcpReceivedMessageHandler, 0);
        Log.e("parti", String.valueOf(initialpart.getConferenceID()));
        bfcp_client newcli = new bfcp_client();
            new Thread(() -> {
                try {
                newcli.BFCPClient();
                bfM = newcli.bfcp_hello_participant(initialpart);
                newcli.sendMessage(bfM);
                bfM = newcli.receiveMessage();
                Log.d("bfm", String.valueOf(bfM.getLength()));
                }catch (IOException e){
                    error =1;
                }
            }).start();
        Log.d("error", "Error" + error);
        etIP = findViewById(R.id.etIP);
        etPort = findViewById(R.id.etPort);
        tvMessages = findViewById(R.id.tvMessages);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);
        Button btnConnect = findViewById(R.id.btnConnect);

        btnConnect.setOnClickListener(v ->  {

        });
        }

//public static bfcp_message received_message_from_server(){
//        bfcp_message message1 = null;
//    return message1;
//
//}
}


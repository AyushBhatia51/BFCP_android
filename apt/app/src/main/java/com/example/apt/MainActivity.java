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
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    Thread Thread1 = null;
    EditText etIP, etPort;
    TextView tvMessages;
    EditText etMessage;
    public static bfcp_message message1 ;
    Button btnSend;
    String SERVER_IP;
    bfcp_message  newmsg;
    Socket socket;
    int SERVER_PORT;
    private bfcp_message bfM;
    DataOutputStream dataOutputStream;
    ObjectOutputStream objout;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bfcp_participant_information initialpart = new bfcp_participant_information();
        bfcp_received_message_handler bfcpReceivedMessageHandler = new bfcp_received_message_handler();
        initialpart.bfcp_initialize_bfcp_participant(3001, 1001, "192.168.8,138", 2345, bfcpReceivedMessageHandler, 0);
        Log.e("parti", String.valueOf(initialpart.getConferenceID()));
        bfcp_client newcli = new bfcp_client();
        int error = newcli.bfcp_hello_participant(initialpart);
        Log.d("error", "Error" + error);
        etIP = findViewById(R.id.etIP);
        etPort = findViewById(R.id.etPort);
        tvMessages = findViewById(R.id.tvMessages);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);
        Button btnConnect = findViewById(R.id.btnConnect);
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMessages.setText("");
                SERVER_IP = etIP.getText().toString().trim();
                SERVER_PORT = Integer.parseInt(etPort.getText().toString().trim());
                Thread1 = new Thread(new Thread1());
                Thread1.start();
            }
        });
        bfcp_client bfcpClient = new bfcp_client();
        newmsg = bfcpClient.message;
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String message = etMessage.getText().toString().trim();

                if (newmsg != null) {
                    new Thread(new Thread3(newmsg)).start();
                }
                System.out.println("hello");
            }
        });


    }

    private PrintWriter output;


    private BufferedReader input;

    class Thread1 implements Runnable {
        public void run() {

            try {
                socket = new Socket(SERVER_IP, SERVER_PORT);
                output = new PrintWriter(socket.getOutputStream());
                objout = new ObjectOutputStream(socket.getOutputStream());
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvMessages.setText("Connected");
                    }
                });
                new Thread(new Thread2()).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class Thread2 implements Runnable {
        bfcp_message message = new bfcp_message();

        @Override
        public void run() {
            while (true) {
                try {
                    String message = input.readLine();

                    if (message != null) {
                        runOnUiThread(() -> tvMessages.append("server: " + message + " "));
                    } else {
                        Thread1 = new Thread(new Thread1());
                        Thread1.start();
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Thread3 implements Runnable {
        private bfcp_message message;

        public Thread3(bfcp_message newmsg) {
            message = newmsg;
        }




        @Override
        public void run() {
//            output.write(String.valueOf(message));
            try {
                objout.writeObject(message);
                objout.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvMessages.append("client: " + message.toString() + " ");
                    etMessage.setText("");
                }
            });
        }
    }
}


package com.example.apt;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apt.Clint.bfcp_arguments;
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
import java.io.ByteArrayOutputStream;
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
    Button btnSend;
    String SERVER_IP ="192.168.8.138";
    bfcp_message message;
    Socket socket;
    int SERVER_PORT =2345;
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
//                SERVER_IP = etIP.getText().toString().trim();
                SERVER_IP = "192.168.8.138";
//                SERVER_PORT = Integer.parseInt(etPort.getText().toString().trim());
                SERVER_PORT = 2345;
                Thread1 = new Thread(new Thread1());
                Thread1.start();
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String message = etMessage.getText().toString().trim();

                if (messagedata() != null) {
                    new Thread(new Thread3(messagedata())).start();
                }
                System.out.println("hello");
            }
        });


        }




      public bfcp_message messagedata(){
          bfcp_arguments arguments = new bfcp_arguments();
          bfcp_client bfcpClient = new bfcp_client();


//        pthread_mutex_lock(&count_mutex);
//
          /* Prepare a new 'Hello' message */
          arguments.primitive = 11;
//        arguments.primitive = null;
          bfcp_entity rrr = new bfcp_entity();
          rrr.conferenceID = 1001;
          rrr.transactionID = 0;
          rrr.userID = 3001;
          arguments.entity = rrr;
          arguments.fID = 2001;
          arguments.frqID = 0;
          arguments.bID = 0;
          arguments.priority = 0;
          arguments.frqInfo = null;
          arguments.beneficiary = null;
          arguments.rs = null;
          arguments.pInfo = null;
          arguments.sInfo = null;
          arguments.error = null;
          arguments.eInfo = null;
          arguments.primitives = null;
          arguments.attributes = null;
          arguments.nonce = 0;
          arguments.digest = null;
          Log.d("exception",String.valueOf(arguments.entity.conferenceID));
          message = bfcpClient.bfcp_build_message(arguments);

          Log.d("Data_from_lib", String.valueOf(message));

          if(message == null) {
//            pthread_mutex_unlock(&count_mutex);
              return null;
          }

//
//          byte[] byteArr = new byte[1000];
//          try {
//              ByteArrayOutputStream bos = new ByteArrayOutputStream();
//              ObjectOutputStream oos = new ObjectOutputStream(bos);
//              oos.writeObject(message);
//              byteArr = bos.toByteArray();
//          } catch (IOException e) {
//              // handle exception
//          }

          return message;
      }

    private PrintWriter output;


    private BufferedReader input;

    class Thread1 implements Runnable {
        public void run() {

            try {
                socket = new Socket(SERVER_IP, SERVER_PORT);
                output = new PrintWriter(socket.getOutputStream());
                objout = new ObjectOutputStream(socket.getOutputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvMessages.setText("Connected");
                    }
                });
                new Thread(new Thread2()).start();
            } catch (IOException e) {
//                e.printStackTrace();
                Log.d("not Connected","to server");
            }
        }
    }

    class Thread2 implements Runnable {


        @Override
        public void run() {
            while (true) {
//                try {
//                    message = input.readLine();
//                } catch (IOException e) {
////                    throw new RuntimeException(e);
//                    Log.d("Unable to read",message);
//                }

                if (message != null) {
                    runOnUiThread(() -> tvMessages.append("server: " + message + " "));
                } else {
                    Thread1 = new Thread(new Thread1());
                    Thread1.start();
                    return;
                }
            }
        }
    }

    class Thread3 implements Runnable {
        private bfcp_message message1;

        public Thread3(bfcp_message newmsg) {
            message1 = newmsg;
        }

        @Override
        public void run() {

            try {
                objout.writeObject(message1);
                Log.d("able to write", String.valueOf(objout));
            } catch (IOException e) {
//                throw new RuntimeException(e);
                Log.d("Unable to write", String.valueOf(objout));

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


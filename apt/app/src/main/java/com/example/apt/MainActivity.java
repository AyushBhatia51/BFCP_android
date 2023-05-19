package com.example.apt;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apt.Clint.bfcp_client;
import com.example.apt.JNIbfcp.*;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    Thread Thread1 = null;
    EditText etIP, etPort;
    TextView tvMessages;
    EditText etMessage;
    Button btnSend,btnConnect;
    String SERVER_IP ="192.168.8.130";
    bfcp_message message;
    Socket socket;
    int SERVER_PORT =1234;
//    private SocketManager socketManager;
    private bfcp_message bfM;
    PrintWriter writer;
    InputStream reader;
    int error;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        socketManager = new SocketManager(SERVER_IP, SERVER_PORT);
        btnConnect = findViewById(R.id.btnConnect);
        btnSend = findViewById(R.id.btnSend);
//        socketManager.connect();
        bfcp_participant_information initialpart = new bfcp_participant_information();
        bfcp_received_message_handler bfcpReceivedMessageHandler = new bfcp_received_message_handler();
        initialpart.bfcp_initialize_bfcp_participant(3001, 1001, "192.168.8.138", 2345, bfcpReceivedMessageHandler, 0);
        Log.e("parti", String.valueOf(initialpart.getConferenceID()));
        bfcp_client newcli = new bfcp_client();
//        btnConnect.setOnClickListener(v -> new Thread(() -> {
//            try {
////
//                bfM = newcli.bfcp_hello_participant(initialpart);
//                socketManager.connect();
//                socketManager.sendMessage(bfM.getBuffer());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }).start());

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectToServerTask connectTask = new ConnectToServerTask();
                connectTask.execute();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                    bfM = newcli.bfcp_hello_participant(initialpart);
                String message = etMessage.getText().toString();
                SendMessageToServerTask sendTask = new SendMessageToServerTask();
                sendTask.execute(message);

            }
        });

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

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        // Disconnect socket when activity is destroyed
//        try {
//            socketManager.disconnect();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//public static bfcp_message received_message_from_server(){
//        bfcp_message message1 = null;
//    return message1;
//
//}



    private class ConnectToServerTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                socket = new Socket(SERVER_IP, SERVER_PORT);
                if(socket.isConnected()){
                    Log.d("connected", "connected: ");
                }
                writer =  new PrintWriter(socket.getOutputStream(), true);
                reader = socket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (socket != null && socket.isConnected()) {
                tvMessages.setText("Connected to server.");
                btnConnect.setEnabled(false);
                btnSend.setEnabled(true);
            } else {
                tvMessages.setText("Connected to server.");
            }
        }
    }

    private class SendMessageToServerTask extends AsyncTask<byte[], Void, byte[]> {

        @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
        @Override
        protected byte[] doInBackground(byte[]... messages) {
            if (writer != null) {
                try {
                    byte[] messageBytes = messages[0]; // Access the first element of the messages array
                    writer.println(messageBytes);
                    Log.d(TAG, "Sent message: " + new String(messageBytes));
//                    byte[] buffer = new byte[1024];
//                    byte[] data = new byte[1024];
//                    BufferedInputStream bis = new BufferedInputStream(reader);
//                    int bytesRead;
//                    while((bytesRead = bis.read(buffer)) != -1){
//                        data = Arrays.copyOf(buffer, bytesRead);

//                    }
                    byte[] data = reader.readAllBytes();

                    return data;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(byte[] serverResponse) {
            if (serverResponse != null) {
//                for(int i = 0 ; i<serverResponse.length; i++) {
                    Log.d("Server acknowledgment: ", "onPostExecute: " + serverResponse);
//                }
            } else {
                Log.d("Server acknowledgment: ","Error communicating with the server.");
            }
        }
    }
}



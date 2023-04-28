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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

//    public static  Object BFCP_CHECK_MESSAGE = null ;
//    Button bt ;
//    BFCPclient bfcpParticipant = new BFCPclient();

    Thread Thread1 = null;
    EditText etIP, etPort;
    TextView tvMessages;
    EditText etMessage;
    Button btnSend;
    String SERVER_IP;
    int SERVER_PORT;

//

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // JNIbfcpclass myclass = new JNIbfcpclass();
        //bfcp_message result = myclass.bfcp_build_message_Hello()
        bfcp_participant_information initialpart = new bfcp_participant_information();
        bfcp_received_message_handler bfcpReceivedMessageHandler =new bfcp_received_message_handler();
        initialpart.bfcp_initialize_bfcp_participant(3001,1001,"192.168.8,138",2345,bfcpReceivedMessageHandler,0);
        Log.e("parti",String.valueOf(initialpart.getConferenceID()));
        bfcp_client newcli = new bfcp_client();
        int error = newcli.bfcp_hello_participant(initialpart);
        Log.d("error","Error"+error);




//        bt = (Button) findViewById(R.id.requestbtn);
//        int conferenceID = 1001;
//        int userID =0 , floorID = 0, benificiaryID = 0;
//        bfcp_participant_information list = new bfcp_participant_information();
//        list.conferenceID = 1001;
//        list.userID= 3001;
//        bfcp_floors_participant node = new bfcp_floors_participant();
//        node.floorID = 1;
//        node.sInfo = 0;
//        list.floors_participant = node;
//        floors_participant temp_floors = null;
//        int transport = -1, error= 0,status=0,port_server=0,query_position=0;
//        short floorRequestID = 0;
//        //list = null;
//        try {
//            error = bfcpParticipant.bfcp_hello_participant(list);
//            BFCP_CHECK_MESSAGE.toString();
//        }catch (Exception e){
//            Log.d("exception",String.valueOf(error));
//        }
//
////        try {
//            bfcp_message bfcpEntity = new bfcp_message();
////            Log.d("hh", String.valueOf(bfcpEntity));
//            bfcpEntity.sethello();
////
////
//     String a = bfcpEntity.gethello();
////
////
//            System.out.println(a);
//            System.out.println(a);
//            Log.d("hh", String.valueOf(a));
////
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
////       String hostname = "192.168.8.138";
////       int port =  2345;
////
////
////
////        Thread gfgThread = new Thread(new Runnable() {
////            @Override
////            public void run() {
////                try (Socket socket = new Socket(hostname, port)) {
////
////                    InputStream input = socket.getInputStream();
////                    InputStreamReader reader = new InputStreamReader(input);
////
////                    int character;
////                    StringBuilder data = new StringBuilder();
////
////                    while ((character = reader.read()) != -1) {
////                        data.append((char) character);
////                    }
////
////                    System.out.println(data);
////
////
////                } catch (UnknownHostException ex) {
////
////                    System.out.println("Server not found: " + ex.getMessage());
////
////                } catch (IOException ex) {
////
////                    System.out.println("I/O error: " + ex.getMessage());
////                }
////                 catch (Exception e) {
////                    e.printStackTrace();
////                }
////            }
////        });
//
//        bfcp_participant_information bfcp_participant_information = new bfcp_participant_information();
////
////        gfgThread.start();
//        bt.setOnClickListener(v -> {
//            BFCPclient.bfcp_hello_participant(bfcp_participant_information);
//
//        });
//    }
//
//
////    public  void  manu(String linestr){
////        String line = null, text= null,text1=null;
////        long conferenceID = 0;
////        int userID =0 , floorID = 0, benificiaryID = 0;
////        bfcp_participant_information list = null;
////        bfcp_floors_participant node = null;
////        floors_participant temp_floors = null;
////        int transport = -1, error= 0,status=0,port_server=0,query_position=0;
////        short floorRequestID = 0;
////        list = null;
////
////
////
////
////        String s= "\n--------PARTICIPANT LIST-----------------------------\n"+
////                " ?      - Show the menu\n"+
////                " c      - Create the Participant\n"+
////                " h      - Destroy the Participant\n"+
////                " i      - Insert a new floor\n"+
////                " d      - Remove a floor\n"+
////                " s      - Show the information of the conference\n"+
////                "BFCP Messages:\n"+
////                " f      - Hello\n"+
////                " r      - FloorRequest\n"+
////                " l      - FloorRelease\n"+
////                " o      - FloorRequestQuery\n"+
////                " u      - UserQuery\n"+
////                " e      - FloorQuery\n"+
////                " a      - ChairAction\n"+
////                " q      - quit                   \n"+
////                "------------------------------------------------------\n\n";
////        TextView txt = (TextView) findViewById(R.id.text);
////        txt.setText(s);
////
////        int i = 0 ;
////        while (i < line.length()){
////            switch(line) {
////                case "?":
////             System.out.println("\"\\n--------PARTICIPANT LIST-----------------------------\\n\",\n" +
////                     "                \" ?      - Show the menu\\n\",\n" +
////                     "                \" c      - Create the Participant\\n\",\n" +
////                     "                \" h      - Destroy the Participant\\n\",\n" +
////                     "                \" i      - Insert a new floor\\n\",\n" +
////                     "                \" d      - Remove a floor\\n\",\n" +
////                     "                \" s      - Show the information of the conference\\n\",\n" +
////                     "                \"BFCP Messages:\\n\",\n" +
////                     "                \" f      - Hello\\n\",\n" +
////                     "                \" r      - FloorRequest\\n\",\n" +
////                     "                \" l      - FloorRelease\\n\",\n" +
////                     "                \" o      - FloorRequestQuery\\n\",\n" +
////                     "                \" u      - UserQuery\\n\",\n" +
////                     "                \" e      - FloorQuery\\n\",\n" +
////                     "                \" a      - ChairAction\\n\",\n" +
////                     "                \" q      - quit                   \\n\",\n" +
////                     "                \"------------------------------------------------------\\n\\n\"");
////                    break;
////
////                case "f":
////                   ++i;
////                   error = bfcpParticipant.bfcp_hello_participant(list);
////                    BFCP_CHECK_MESSAGE.toString();
////                    break;
////                default:break;
////            }
////            i++;
////        }
////
////    }
//
//
//
//
//}

        bfcp_message bfcpMessage = new bfcp_message();
        bfcpMessage.sethello();
        bfcp_message a = bfcpMessage.gethello();

        System.out.println(a);
        System.out.println(a);
        Log.d("hh", String.valueOf(a));
//

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
//        bfcp_message finalBfcpMessage = bfcpMessage;
        System.out.println("hhhhhhhhhh1");
//        btnSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String message = etMessage.getText().toString().trim();
//                System.out.println("hhhhhhhhhh");
//                if (!message.isEmpty()) {
//                    new Thread(new Thread3(a)).start();
//
//                }
//            }
//        });
    }
    private PrintWriter output;
    private BufferedReader input;
    class Thread1 implements Runnable {
        public void run() {
            Socket socket;

            try {
                socket = new Socket(SERVER_IP, SERVER_PORT);

                output = new PrintWriter(socket.getOutputStream());
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
        bfcp_message message =new bfcp_message() ;
        @Override
        public void run() {
            while (true) {
                try {
                      String message = input.readLine();
                    if (message != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvMessages.append("server: " + message + " ");
                            }
                        });
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
    //class Thread3 implements Runnable {
//        private String message;
//          bfcp_message message ;
//        Thread3(String message) {
//            bfcp_message.message = message;
//        }
//        @Override
//        public void run() {
//            output.write(String.valueOf(message));
//            output.flush();
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    tvMessages.append("client: " + message + " ");
//                    etMessage.setText("");
//                }
//            });
//        }
//    }
}
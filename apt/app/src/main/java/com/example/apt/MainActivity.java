package com.example.apt;
import java.net.*;
import java.io.*;
import static java.sql.Types.NULL;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apt.Clint.BFCPclient;
import com.example.apt.JNIbfcp.Conference_participent;
import com.example.apt.JNIbfcp.bfcp_participant_information;
import com.example.apt.JNIbfcp.*;
import com.example.apt.JNIbfcp.*;

import android.os.Bundle;
import android.util.Log;
import android.widget.Switch;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    public static  Object BFCP_CHECK_MESSAGE = null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int conferenceID = 1001;
        int userID =0 , floorID = 0, benificiaryID = 0;
        bfcp_participant_information list = new bfcp_participant_information();
        list.conferenceID = 1001;
        list.userID= 3001;
        bfcp_floors_participant node = new bfcp_floors_participant();
        node.floorID = 1;
        node.sInfo = 0;
        list.floors_participant = node;
        floors_participant temp_floors = null;
        int transport = -1, error= 0,status=0,port_server=0,query_position=0;
        short floorRequestID = 0;
        //list = null;
        try {
            error = bfcpParticipant.bfcp_hello_participant(list);
            BFCP_CHECK_MESSAGE.toString();
        }catch (Exception e){
            Log.d("exception",String.valueOf(error));
        }

        try {
            bfcp_entity bfcpEntity = new bfcp_entity();
            Log.d("hh", String.valueOf(bfcpEntity));
            bfcpEntity.sethello();


            String a = bfcpEntity.gethello();


            System.out.println(a);
            System.out.println(a);
            Log.d("hh", String.valueOf(a));


        } catch (Exception e) {
            System.out.println(e);
        }

//       String hostname = "192.168.8.138";
//       int port =  2345;
//
//
//
//        Thread gfgThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try (Socket socket = new Socket(hostname, port)) {
//
//                    InputStream input = socket.getInputStream();
//                    InputStreamReader reader = new InputStreamReader(input);
//
//                    int character;
//                    StringBuilder data = new StringBuilder();
//
//                    while ((character = reader.read()) != -1) {
//                        data.append((char) character);
//                    }
//
//                    System.out.println(data);
//
//
//                } catch (UnknownHostException ex) {
//
//                    System.out.println("Server not found: " + ex.getMessage());
//
//                } catch (IOException ex) {
//
//                    System.out.println("I/O error: " + ex.getMessage());
//                }
//                 catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        gfgThread.start();
    }
    BFCPclient bfcpParticipant = new BFCPclient();


    public  void  manu(String linestr){
        String line = null, text= null,text1=null;
        long conferenceID = 0;
        int userID =0 , floorID = 0, benificiaryID = 0;
        bfcp_participant_information list = null;
        bfcp_floors_participant node = null;
        floors_participant temp_floors = null;
        int transport = -1, error= 0,status=0,port_server=0,query_position=0;
        short floorRequestID = 0;
        list = null;




        String s= "\n--------PARTICIPANT LIST-----------------------------\n"+
                " ?      - Show the menu\n"+
                " c      - Create the Participant\n"+
                " h      - Destroy the Participant\n"+
                " i      - Insert a new floor\n"+
                " d      - Remove a floor\n"+
                " s      - Show the information of the conference\n"+
                "BFCP Messages:\n"+
                " f      - Hello\n"+
                " r      - FloorRequest\n"+
                " l      - FloorRelease\n"+
                " o      - FloorRequestQuery\n"+
                " u      - UserQuery\n"+
                " e      - FloorQuery\n"+
                " a      - ChairAction\n"+
                " q      - quit                   \n"+
                "------------------------------------------------------\n\n";
        TextView txt = (TextView) findViewById(R.id.text);
        txt.setText(s);

        int i = 0 ;
        while (i < line.length()){
            switch(line) {
                case "?":
             System.out.println("\"\\n--------PARTICIPANT LIST-----------------------------\\n\",\n" +
                     "                \" ?      - Show the menu\\n\",\n" +
                     "                \" c      - Create the Participant\\n\",\n" +
                     "                \" h      - Destroy the Participant\\n\",\n" +
                     "                \" i      - Insert a new floor\\n\",\n" +
                     "                \" d      - Remove a floor\\n\",\n" +
                     "                \" s      - Show the information of the conference\\n\",\n" +
                     "                \"BFCP Messages:\\n\",\n" +
                     "                \" f      - Hello\\n\",\n" +
                     "                \" r      - FloorRequest\\n\",\n" +
                     "                \" l      - FloorRelease\\n\",\n" +
                     "                \" o      - FloorRequestQuery\\n\",\n" +
                     "                \" u      - UserQuery\\n\",\n" +
                     "                \" e      - FloorQuery\\n\",\n" +
                     "                \" a      - ChairAction\\n\",\n" +
                     "                \" q      - quit                   \\n\",\n" +
                     "                \"------------------------------------------------------\\n\\n\"");
                    break;

                case "f":
                   ++i;
                   error = bfcpParticipant.bfcp_hello_participant(list);
                    BFCP_CHECK_MESSAGE.toString();
                    break;
                default:break;
            }
            i++;
        }

    }




}
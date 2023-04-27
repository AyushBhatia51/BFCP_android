package com.example.apt.JNIbfcp;

public class bfcp_participant_information {
    public static int conferenceID;		/* Conference Identifier */
    public static int userID;			/* User Identifier (the participant) */
   public static bfcp_floors_participant floors_participant;

//
    public void bfcp_initialize_bfcp_participant(int conferenceID, int userID, String IP_address_server, int port_server, bfcp_received_message_handler receivedMsg, int transport){
        this.conferenceID = conferenceID;
        this.userID = userID;
        this.floors_participant = null;

    }
    public int getConferenceID(){
        return conferenceID;
    }
    public int getUserID(){
        return userID;
    }
    public bfcp_floors_participant getFloors_participant(){
        return floors_participant;
    }

}

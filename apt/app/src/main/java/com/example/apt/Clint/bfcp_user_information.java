package com.example.apt.Clint;

public class bfcp_user_information {
    public int ID;         // For the INFORMATION-HEADER
    public String display; // USER-DISPLAY-NAME, optional
    public String uri;     // USER-URI, optional

    public bfcp_user_information(int ID, String display, String uri) {
        this.ID = ID;
        this.display = display;
        this.uri = uri;
    }
}

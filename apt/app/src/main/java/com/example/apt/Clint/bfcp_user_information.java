package com.example.apt.Clint;

public class bfcp_user_information {
    public short ID;         // For the INFORMATION-HEADER
    public char display; // USER-DISPLAY-NAME, optional
    public char uri;     // USER-URI, optional

    public bfcp_user_information(short ID, char display, char uri) {
        this.ID = ID;
        this.display = display;
        this.uri = uri;
    }
}

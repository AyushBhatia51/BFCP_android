package com.example.apt.Clint;

public class bfcp_digest {
    public short algorithm;     // (currently UNUSED)
    public char text;       // (currently UNUSED)

    public bfcp_digest(short algorithm, char text) {
        this.algorithm = algorithm;
        this.text = text;
    }
}

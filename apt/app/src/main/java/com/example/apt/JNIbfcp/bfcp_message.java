package com.example.apt.JNIbfcp;

import java.io.Serializable;

public class bfcp_message implements Serializable {
    private byte[] buffer;
    private short position;
    private short length;

    public bfcp_message(byte[] buffer, short[] posAndLen) {
        this.buffer = buffer;
        this.position = posAndLen[0];
        this.length = posAndLen[1];
    }

    public byte[] getBuffer() {
        return buffer;
    }

    public void setPosition(short position) {
        this.position = position;
    }

    public short getPosition() {
        return position;
    }

    public void setLength(short length) {
        this.length = length;
    }

    public short getLength() {
        return length;
    }
}

package com.pedro.rtpstreamer.utils;

import java.util.Date;

public class MessageClass {
    private int id;
    private Date timestamp;
    private AuthClass user;
    private String message;

    public MessageClass(int id, Date timestamp, AuthClass userName, String message) {
        this.id = id;
        this.timestamp = timestamp;
        this.user = userName;
        this.message = message;
    }

    protected String getMessage(){
        return message;
    }
    public int getId() {
        return id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public AuthClass getUser() {
        return user;
    }
}

package com.pedro.rtpstreamer.utils;

public class AuthClass {
    private String username;
    private int personId;
    private int roomId;
    private String privateKey;
    private String publicKey;

    public AuthClass(String username, int personId, int roomId, String privateKey, String publicKey) {
        this.username = username;
        this.personId = personId;
        this.roomId = roomId;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public String getUsername() {
        return username;
    }

    public int getPersonId() {
        return personId;
    }
    public int getRoomId() {
        return roomId;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }


}

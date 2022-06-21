package com.pedro.rtpstreamer.utils;

public class AuthClass {
    private String username;
    private String personId;
    private String roomId;
    private String privateKey;
    private String publicKey;

    public AuthClass(String username, String personId, String roomId, String privateKey, String publicKey) {
        this.username = username;
        this.personId = personId;
        this.roomId = roomId;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public String getUsername() {
        return username;
    }

    public String getPersonId() {
        return personId;
    }
    public String getRoomId() {
        return roomId;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }


}

package com.pedro.rtpstreamer.utils;

public class AuthClass {
    private String username;
    private int roomId;
    private String privateKey;
    private String publicKey;

    public AuthClass(String username, int roomId, String privateKey, String publicKey) {
        this.username = username;
        this.roomId = roomId;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public String getUsername() {
        return username;
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

    public void setUsername(String username) {
        this.username = username;
    }
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

}

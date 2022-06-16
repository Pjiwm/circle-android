package com.pedro.rtpstreamer.utils;

public class AuthClass {
    private String username;
    private String privateKey;
    private String publicKey;

    public AuthClass(String username, String privateKey, String publicKey) {
        this.username = username;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public String getUsername() {
        return username;
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

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

}

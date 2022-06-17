package com.pedro.rtpstreamer.utils;

public class PublicKeysClass {
    private String username;
    private String publicKey;

    public PublicKeysClass(String username, String publicKey) {
        this.username = username;
        this.publicKey = publicKey;
    }

    public String getUsername() {
        return username;
    }

    public String getPublicKey() {
        return publicKey;
    }

}

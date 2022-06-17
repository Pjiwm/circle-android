package com.pedro.rtpstreamer.utils;

public class PublicKeysClass {
    private String username;
    private int personId;
    private String publicKey;

    public PublicKeysClass(String username, int personId, String publicKey) {
        this.username = username;
        this.personId = personId;
        this.publicKey = publicKey;
    }

    public String getUsername() {
        return username;
    }
    public int getPersonId() {
        return personId;
    }

    public String getPublicKey() {
        return publicKey;
    }

}

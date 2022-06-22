package com.pedro.rtpstreamer.utils;

import android.util.Base64;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class KeyUtilsDemo {
    public static void jsDemo(){
        System.out.println("Start of encrypt test");
        JSONObject jsonObject = new JSONObject();
        PrivateKey privateKey = null;
        PublicKey publicKey = null;
        byte[] henk = "Henk".getBytes(StandardCharsets.UTF_8);
        System.out.println("henk: " + java.util.Base64.getEncoder().encodeToString(henk));
        String henkBase64 = java.util.Base64.getEncoder().encodeToString(henk);
        System.out.println("henk decoded: "+ new String(java.util.Base64.getDecoder().decode(henkBase64),StandardCharsets.UTF_8));
        System.out.println();
        String encrypted = "bN5CrmfBQAxACil0nhPrN18Mo3te0zSMt6OZNG0JJOZUQg19oRLCmOLq7AsltNvvPRp1+RruR1EELf0/ghiSdtGWE7S+TxXSjO0xRe0X/a42XM7TCqNYp0L4uKLtz3xN/VG3hpeb8CAifI5+MLHJ6ebdwNVma5YxQ0atyE/XBVM=";
        try {
            jsonObject.put("name", "Henk");
            privateKey = KeyUtils.stringToPrivateKey("-----BEGIN PRIVATE KEY-----MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKsdL88D0AYecNAjQUhVN4U5tEydER/ZccRrwEqK8BxrEomKtz5hRGtCVrNCw4e9kKoHTTJk03wVSawJ43R/fs1zYygdNM0vAD0BUWilqGwtMTtHteAXAe1vHCSBUUfJIAhWuFhlvM0hooZ2jc3YSwR1XMgdMnHKTtZ1+6Sivqr/AgMBAAECgYB1g4Xh1ex3R1CSoKOk2WFXzVlVhEjOZrcK/jUA/IoJ+j6w1uMhpAso3lZMpf1Cq50+J+WMRlI3VSdTTzc2OpkJvzi/wngJCxAk+Lut3R3nX0LrunVYxeRFaQhDfPV4cEEVTQXBhN8qKK0ENAhaDOLgXDhVuhOes4AV9yqSd4/mAQJBAOQnfsFscGIZBuQTnIdGFA6bn/KczAdzVgjF1SUxe78V08BiJM0Mk18Um53Wi1JFB7oWJmhjZiVxJfcNHcG1n48CQQC//4Mpxqiui8AHZXA+WfI1dZnkJ0HF2+HbqDw4COx+7bBdwlM0woX6e3X9I0uA2oCDcJMKZG1vXH9+SqlEZoWRAkBgj5qlEFkMG52AABDaa71nZVQvoU70xEqgUeJg6G2ANSO2o5MBuZXRwu4NwlSa0B5yEpfUzGTZ6eZwhfuXFWBfAkBXcu+w5XULG0f5lnBLVfiF9OyFCIRitEXpIHVdKGNfZK+B5PK1Ayy26+jPxXWsWNlUBMBUaW2W3VCMm0EBkJihAkBiEdZ3NRNQ4y/uZgw1nqMFewlxBFC5bhBqo8HxcGIJCGYhavYSXK23pXOtpCwfYE+0FKcUBcR6WvJ+qAqg4PlK-----END PRIVATE KEY-----");
            publicKey = KeyUtils.stringToPublicKey("-----BEGIN PUBLIC KEY-----MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCrHS/PA9AGHnDQI0FIVTeFObRMnREf2XHEa8BKivAcaxKJirc+YURrQlazQsOHvZCqB00yZNN8FUmsCeN0f37Nc2MoHTTNLwA9AVFopahsLTE7R7XgFwHtbxwkgVFHySAIVrhYZbzNIaKGdo3N2EsEdVzIHTJxyk7Wdfukor6q/wIDAQAB-----END PUBLIC KEY-----");
        } catch (Exception e){
            System.out.println("error" + e);
        }
        System.out.println("private key " + privateKey);
        System.out.println("Hello world in base64: "+ Base64.decode(new String("SGVsbG8gV29ybGQ=").getBytes(StandardCharsets.UTF_8),Base64.DEFAULT));
        byte[] asBytes = java.util.Base64.getDecoder().decode(encrypted);
        System.out.println(asBytes);
        try {
            byte[] ownEncrypted = KeyUtils.encrypt(KeyUtils.jsonObjectToByteArray(jsonObject),privateKey);
            String ownEncryptedString = java.util.Base64.getEncoder().encodeToString(ownEncrypted);
            System.out.println(ownEncryptedString);
            System.out.println("ownEncrypted: " + java.util.Base64.getEncoder().encodeToString(ownEncrypted));
          String output = KeyUtils.decrypt(asBytes,KeyUtils.jsonObjectToByteArray(jsonObject),publicKey);
//            String output = KeyUtils.decrypt(ownEncrypted,KeyUtils.jsonObjectToByteArray(jsonObject),publicKey);
//            System.out.println("output of decrypt: " + output);
//            String output = KeyUtils.decrypt(ownEncrypted,KeyUtils.jsonObjectToByteArray(jsonObject),publicKey);
//          System.out.println("output of decrypt: " + output);
        } catch (Exception e) {
            System.out.println("encryption error");
            e.printStackTrace();
        }
    }
    public static void simpleDemo(){
//         Example to test encryption
            System.out.println("keygen starts here \n");
            String message = "hello world";
            KeyPair keys = KeyUtils.keyGen();
            try {
              byte[] encryptedData = KeyUtils.encrypt(KeyUtils.stringToByteArray(message),keys.getPrivate());
              System.out.println("method result" + KeyUtils.decrypt(encryptedData,KeyUtils.stringToByteArray(message),keys.getPublic()));
            } catch (Exception e) {
              System.out.println("encryption error");
              e.printStackTrace();
            }
    }
}

package com.pedro.rtpstreamer.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.UUID;

import javax.crypto.Cipher;

public class KeyUtils {
    // Example to test encryption
    //    System.out.println("keygen starts here \n");
    //    String message = "hello world";
    //    KeyPair keys = KeyUtils.keyGen();
    //    try {
    //      byte[] encryptedData = KeyUtils.encrypt(KeyUtils.stringToByteArray(message),keys.getPrivate());
    //      System.out.println("method result" + KeyUtils.decrypt(encryptedData,KeyUtils.stringToByteArray(message),keys.getPublic()));
    //    } catch (Exception e) {
    //      System.out.println("encryption error");
    //      e.printStackTrace();
    //    }
    /**
     * Generates a public-private key pair
     * @return KeyPair a pair with a public and private key. Should always return keys.
     */
    public static KeyPair keyGen(){
        System.out.println("-------Generate public and private key------");
        try {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        return new KeyPair(publicKey,privateKey);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return null;
    }
    /**
     * Signs a bytearray with custom signature (hash with uuid).
     * @param data The bytearray that will be signed
     * @param privateKey The private key to be used in the encryption
     * @return A byte array with an encrypted signature
     */
    public static byte[] encrypt(byte[] data, PrivateKey privateKey) throws Exception{
        System.out.println("\n---ENCRYPTION STARTED---");
        //Generate hash and UUID
        String hash = KeyUtils.sha1Hash(data);
        String uuid = UUID.randomUUID().toString();
        System.out.println("Hash before encryption: " + hash);
        System.out.println("UUID before encryption: " + uuid);

        //Put hash and UUID in JSON array for compatibility with Javascript
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(hash);
        jsonArray.put(uuid);
        byte[] encryptedData;
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            System.out.println("string: " + jsonArray);
            //Cipher can only process byte arrays
            //Conversion goes as follows JSONArray -> String (utf-8) -> Byte array. Byte array is encrypted
            encryptedData = cipher.doFinal(jsonArrayToByteArray(jsonArray));

        System.out.println("---ENCRYPTION COMPLETE--");
        return encryptedData;
    }
    /**
     * Decrypts the custom signature (hash and uuid) and checks the hash of the object and the decrypted hash
     * @param cypher The bytearray that will be signed
     * @param publicKey The public key to be used to decrypt the signature
     * @return null if the key is not valid or with decryption errors. Otherwise, the UUID will be returned
     */
    public static String decrypt(byte[] cypher, byte[] object, PublicKey publicKey) throws Exception{
        System.out.println("\n---DECRYPTION STARTED---");
        byte[] decryptedData;
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        try{
            decryptedData = cipher.doFinal(cypher);
        } catch (Exception e){
            throw new IllegalArgumentException("Key not valid");
        }
        System.out.println("---DECRYPTION COMPLETE--");
        //Conversion goes as follows Byte array -> String (utf-8) -> JSONArray
        JSONArray jsonArray = byteArrayToJsonArray(decryptedData);
        //index 0 = hash, index 1 = UUID
        //Hash the object and compare it to decrypted hash
        String hash = sha1Hash(object);
        boolean isValid = hash.equals(jsonArray.getString(0));
        System.out.println("Hash after decryption: " + jsonArray.get(0));
        System.out.println("UUID after decryption: " + jsonArray.get(1));
        if(isValid){
            System.out.println("Hash is valid");
            return jsonArray.getString(1);
        }
        System.out.println("Hash is not valid");
        return null;
    }
    //Not tested!!
    public static PrivateKey readPrivateKeyFromFile(String fileName) throws Exception {
        FileInputStream fis;
        ObjectInputStream ois;
        fis = new FileInputStream(fileName);
        ois = new ObjectInputStream (fis);
        PrivateKey privateKey = (PrivateKey) ois.readObject();
        ois.close();
        fis.close();

        return privateKey;
        }
    //Not tested!!
    private static PublicKey readPublicKeyFromFile(String fileName) throws Exception
    {
        FileInputStream fis;
        ObjectInputStream ois;
        fis = new FileInputStream(fileName);
        ois = new ObjectInputStream (fis);
        PublicKey publicKey = (PublicKey) ois.readObject();
        ois.close();
        fis.close();

        return publicKey;
    }
    public static String sha1Hash(byte[] input){
        try {
            // getInstance() method is called with algorithm SHA-1
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input);

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Converts String to ByteArray
     * @param string string to be converted
     * @return Converted string as ByteArray
     */
    public static byte[] stringToByteArray(String string){
        return string.getBytes(StandardCharsets.UTF_8);
    }
    public static byte[] jsonObjectToByteArray(JSONObject jsonObject){
        //Conversion goes as follows JSONObject -> String (utf-8) -> Byte array
        return jsonObject.toString().getBytes(StandardCharsets.UTF_8);
    }
    public static byte[] jsonArrayToByteArray(JSONArray jsonArray){
        //Conversion goes as follows JSONArray// -> String (utf-8) -> Byte array
        return jsonArray.toString().getBytes(StandardCharsets.UTF_8);
    }
    public static JSONObject byteArrayToJsonObject(byte[] byteArray) throws JSONException{
        //Conversion goes as follows Byte array -> String (utf-8) -> JSONObject
        return new JSONObject(new String(byteArray, StandardCharsets.UTF_8));
    }
    public static JSONArray byteArrayToJsonArray(byte[] byteArray) throws JSONException {
        //Conversion goes as follows Byte array -> String (utf-8) -> JSONArray
        return new JSONArray(new String(byteArray, StandardCharsets.UTF_8));
    }


}
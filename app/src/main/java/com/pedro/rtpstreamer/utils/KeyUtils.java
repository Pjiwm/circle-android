package com.pedro.rtpstreamer.utils;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class KeyUtils {
    /**
     * Generates a public-private key pair
     * @return KeyPair a pair with a public and private key. Should always return keys.
     */
    public static KeyPair keyGen(){
        System.out.println("-------Generate public and private key------");
        try {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return null;
    }
    public static PrivateKey stringToPrivateKey(String private_key)
            throws NoSuchAlgorithmException,
            NoSuchPaddingException,
            InvalidKeyException,
            IllegalBlockSizeException,
            BadPaddingException {

        try {
            // Remove the "BEGIN" and "END" lines, as well as any whitespace

            String pkcs8Pem = private_key.toString();
            pkcs8Pem = pkcs8Pem.replace("-----BEGIN PRIVATE KEY-----", "");
            pkcs8Pem = pkcs8Pem.replace("-----END PRIVATE KEY-----", "");
            pkcs8Pem = pkcs8Pem.replaceAll("\\s+", "");
            pkcs8Pem = pkcs8Pem.replaceAll("\\n", "");

            // Base64 decode the result

            byte[] pkcs8EncodedBytes = java.util.Base64.getDecoder().decode(pkcs8Pem);

            // Extract the private key

            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pkcs8EncodedBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(keySpec);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static PublicKey stringToPublicKey(String publicKey)
            throws NoSuchAlgorithmException,
            NoSuchPaddingException,
            InvalidKeyException,
            IllegalBlockSizeException,
            BadPaddingException {

        try {
            // Remove the "BEGIN" and "END" lines, as well as any whitespace

            String pkcs8Pem = publicKey.toString();
            pkcs8Pem = pkcs8Pem.replace("-----BEGIN PUBLIC KEY-----", "");
            pkcs8Pem = pkcs8Pem.replace("-----END PUBLIC KEY-----", "");
            pkcs8Pem = pkcs8Pem.replaceAll("\\s+", "");
            pkcs8Pem = pkcs8Pem.replaceAll("\\n", "");

            // Base64 decode the result
            byte[] pkcs8EncodedBytes = java.util.Base64.getDecoder().decode(pkcs8Pem);

            // Extract the private key
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(pkcs8EncodedBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePublic(keySpec);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return null;
        }
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
        System.out.println("hash " + hash);
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
    public static String decrypt(String signature, byte[] object, PublicKey publicKey) throws Exception {
        byte[] signatureByteArray = java.util.Base64.getDecoder().decode(signature);
        return decrypt(signatureByteArray,object,publicKey);
    }
    /**
     * Decrypts the custom signature (hash and uuid) and checks the hash of the object and the decrypted hash
     * @param cypher The bytearray that will be signed
     * @param publicKey The public key to be used to decrypt the signature
     * @return null if the key is not valid or with decryption errors. Otherwise, the UUID will be returned
     */
    public static String decrypt(byte[] signature, byte[] object, PublicKey publicKey) throws Exception{
        System.out.println("\n---DECRYPTION STARTED---");
        byte[] decryptedData;
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        try{
            decryptedData = cipher.doFinal(signature);
        } catch (Exception e){
            throw new IllegalArgumentException(e);
        }
        System.out.println("---DECRYPTION COMPLETE--");
        //Hack to remove leading 0s 1s and -1s from js code
        decryptedData = KeyUtils.removeInts(decryptedData);
        //Conversion goes as follows Byte array -> String (utf-8) -> JSONArray
        System.out.println("Decrypted String: " + new String(decryptedData,StandardCharsets.UTF_8));
        JSONArray jsonArray = byteArrayToJsonArray(decryptedData);
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
    public static String sha1Hash(byte[] toHash){
        HashCode hashCode = Hashing.sha1().hashBytes(toHash);
        return hashCode.toString();
    }
    private static byte[] removeInts(byte[] arr){
        // Count the leading zeros:
        int zeroes = 0;
        while (arr[zeroes] == 0 || arr[zeroes] == 1 || arr[zeroes] == -1) {
            ++zeroes;
        }

        //check if all the numbers are 0 and last is 1
        return Arrays.copyOfRange(arr,zeroes, arr.length);
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
        String string =  new String(byteArray, StandardCharsets.UTF_8);
        return new JSONArray(string);
    }


}
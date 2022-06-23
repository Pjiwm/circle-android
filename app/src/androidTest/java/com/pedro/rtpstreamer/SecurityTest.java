package com.pedro.rtpstreamer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import android.util.Base64;
import android.util.Log;

import androidx.test.*;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.pedro.rtpstreamer.utils.AuthClass;
import com.pedro.rtpstreamer.utils.AuthData;
import com.pedro.rtpstreamer.utils.KeyUtils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.*;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

public class SecurityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> aRule = new ActivityScenarioRule<>(MainActivity.class);
    public KeyUtils keyUtils = new KeyUtils();

    AuthData authData = new AuthData();
    private AuthClass[] accounts = authData.getAuthData();
    private AuthClass currentUser = accounts[0];
    private Base64 b64;

    private String message2 = "unknown";
    private String message1 = "secret";
    private String hash1;
    private String hash2;
    private String signature1;
    private String signature2;
    private String ans1;
    private String ans2;


    @Test
    public void ifHashingAStringCreatesAHash() {
        byte[] bAr = keyUtils.stringToByteArray(message1);
        hash1 = keyUtils.sha1Hash(bAr);
        assertNotEquals(message1, hash1);
    }

    @Test
    public void ifUUIDMakesTheEncryptionUnique() throws Exception {
        PublicKey pub = keyUtils.stringToPublicKey(accounts[0].getPublicKey());
        PrivateKey pri = keyUtils.stringToPrivateKey(accounts[0].getPrivateKey());
        byte[] b1 = keyUtils.encrypt(keyUtils.stringToByteArray(message1), pri);
        byte[] b2 = keyUtils.encrypt(keyUtils.stringToByteArray(message1), pri);
        assertNotEquals(b1, b2);
    }

    @Test
    public void ifUUIDMakesTheDecryptionUniqueOfSameDifferentVariables() throws Exception {
        PublicKey pub = keyUtils.stringToPublicKey(accounts[0].getPublicKey());
        PrivateKey pri = keyUtils.stringToPrivateKey(accounts[0].getPrivateKey());
        byte[] b1 = keyUtils.encrypt(keyUtils.stringToByteArray(message1), pri);
        byte[] b2 = keyUtils.encrypt(keyUtils.stringToByteArray(message1), pri);
        ans1 = keyUtils.decrypt(b1, keyUtils.stringToByteArray(message1), pub);
        ans2 = keyUtils.decrypt(b2, keyUtils.stringToByteArray(message1), pub);
        assertNotEquals(ans1, ans2);
    }

    @Test
    public void ifUUIDMakesTheDecryptionNotUniqueOfSameEncryptedVariables() throws Exception {
        PublicKey pub = keyUtils.stringToPublicKey(accounts[0].getPublicKey());
        PrivateKey pri = keyUtils.stringToPrivateKey(accounts[0].getPrivateKey());
        byte[] b1 = keyUtils.encrypt(keyUtils.stringToByteArray(message1), pri);
        ans1 = keyUtils.decrypt(b1, keyUtils.stringToByteArray(message1), pub);
        ans2 = keyUtils.decrypt(b1, keyUtils.stringToByteArray(message1), pub);
        assertEquals(ans1, ans2);
    }

    @Test
    public void if30SecondsGetAddedToDate() throws Exception {
        Date date = new Date();
        int seconds = -30;
        Date newDate = MainActivity.addSecondsToDate(date, -30);
        assertNotEquals(date, newDate);
    }

    @Test
    public void ifUuidListGetsRenewed() throws Exception {
        Date newDate = new Date();
        Date oldDate = MainActivity.addSecondsToDate(newDate, -372);
        Date okDate = MainActivity.addSecondsToDate(newDate, -3);
        JSONObject jOb1 = new JSONObject();
        jOb1.put("uuid", "dsahdkjhsakjhdk");
        jOb1.put("date", newDate.toString());
        JSONObject jOb2 = new JSONObject();
        jOb2.put("uuid", "tyrtyrthfdgdfgf");
        jOb2.put("date", oldDate.toString());
        JSONObject jOb3 = new JSONObject();
        jOb3.put("uuid", "vbnckxzjnbckxzx");
        jOb3.put("date", okDate.toString());
        JSONArray jAr1 = new JSONArray();
        jAr1.put(jOb1);
        jAr1.put(jOb2);
        jAr1.put(jOb3);
        JSONArray jAr2 = MainActivity.renewUuidList(jAr1);
        assertEquals(2, jAr2.length());
    }

}

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

import org.junit.Rule;
import org.junit.Test;
import org.junit.*;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

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

//    @Before
//    public void beforeEveryTest() throws Exception {
//        hash1 = MainActivity.sha256String(message1);
//        hash2 = MainActivity.sha256String(message2);
//        signature1 = MainActivity.encrypt(hash1, currentUser, b64);
//        signature2 = MainActivity.encrypt(hash2, currentUser, b64);
//    }

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
    public void ifAnEncryptedMessageCanBeVerified() throws Exception {
        PublicKey pub = keyUtils.stringToPublicKey(accounts[0].getPublicKey());
        PrivateKey pri = keyUtils.stringToPrivateKey(accounts[0].getPrivateKey());
        byte[] b1 = keyUtils.encrypt(keyUtils.stringToByteArray(message1), pri);
        byte[] b2 = keyUtils.encrypt(keyUtils.stringToByteArray(message1), pri);
        Log.v("VVVVVVV", "b1: " + b1 + " - b2: " + b2);
        ans1 = keyUtils.decrypt(b1, keyUtils.stringToByteArray(message1), pub);
        ans2 = keyUtils.decrypt(b2, keyUtils.stringToByteArray(message1), pub);
        Log.v("VVVVVVV", "ans1: " + ans1 + " - ans2: " + ans2);
        assertEquals(ans1, ans2);
    }

}

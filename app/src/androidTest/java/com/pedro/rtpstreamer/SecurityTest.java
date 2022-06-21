package com.pedro.rtpstreamer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import android.util.Base64;

import androidx.test.*;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.pedro.rtpstreamer.utils.AuthClass;
import com.pedro.rtpstreamer.utils.AuthData;

import org.junit.Rule;
import org.junit.Test;
import org.junit.*;

public class SecurityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> aRule = new ActivityScenarioRule<>(MainActivity.class);

    AuthData authData = new AuthData();
    private AuthClass[] accounts = authData.getAuthData();
    private AuthClass currentUser = accounts[0];
    private Base64 b64;

    private String message1 = "secret";
    private String message2 = "unknown";
    private String hash1;
    private String hash2;
    private String signature1;
    private String signature2;

    @Before
    public void beforeEveryTest() throws Exception {
        hash1 = MainActivity.sha256String(message1);
        hash2 = MainActivity.sha256String(message2);
        signature1 = MainActivity.encrypt(hash1, currentUser, b64);
        signature2 = MainActivity.encrypt(hash2, currentUser, b64);
    }

    @Test
    public void TheSha256StringMethodShouldHashTheInputString() {
        hash1 = MainActivity.sha256String(message1);
        assertNotEquals(message1, hash1);
    }

    @Test
    public void TheSha256StringMethodShouldHashTheInputStringConsistent() {
        hash1 = MainActivity.sha256String(message1);
        hash2 = MainActivity.sha256String(message1);
        assertEquals(hash1, hash2);
    }

    @Test
    public void TheEncryptMethodShouldEncryptTheInputStringAndReturnItUsingTheEncodeMethod() throws Exception {
        signature1 = MainActivity.encrypt(hash1, currentUser, b64);
        assertNotEquals(hash1, signature1);
    }

    @Test
    public void TheDecryptMethodShouldDecryptTheInputStringAndReturnItUsingTheDecodeMethod() throws Exception {
        hash1 = MainActivity.decrypt(signature1, currentUser, b64);
        assertNotEquals(hash1, signature1);
    }

    @Test
    public void ADecryptedSignatureHashShouldBeTheSameAsAHashedMessage() throws Exception {
        hash1 = MainActivity.decrypt(signature1, currentUser, b64);
        hash2 = MainActivity.sha256String(message1);
        assertEquals(hash1, hash2);
    }

    @Test
    public void AnEncryptedMessageShouldBeTheSameAfterItHasBeenDecrypted() throws Exception {
        hash1 = MainActivity.encrypt(message1, currentUser, b64);
        message2 = MainActivity.decrypt(hash1, currentUser, b64);
        assertEquals(message1, message2);
    }

}

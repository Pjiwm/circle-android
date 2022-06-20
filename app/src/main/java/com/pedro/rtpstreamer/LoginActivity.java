package com.pedro.rtpstreamer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pedro.rtpstreamer.utils.AuthClass;
import com.pedro.rtpstreamer.utils.AuthData;

public class LoginActivity extends AppCompatActivity {

    AuthData authData = new AuthData();
    private EditText mUsernameTextView;
    private Button mLoginButton;
    private AuthClass[] accounts = authData.getAuthData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logintp);

        mUsernameTextView = findViewById(R.id.username);
        mLoginButton = findViewById(R.id.login);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUsernameTextView.getText().equals(accounts[3].getUsername())) {
                    Toast.makeText(LoginActivity.this,"Username and Private key combination is Correct",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(LoginActivity.this,"Username and Private key combination is Incorrect",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
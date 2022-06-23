package com.pedro.rtpstreamer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.pedro.rtpstreamer.utils.AuthClass;
import com.pedro.rtpstreamer.utils.AuthData;
import com.pedro.rtpstreamer.utils.KeyUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.PrivateKey;

public class LoginActivity extends AppCompatActivity {

    AuthData authData = new AuthData();
    private EditText mUsernameTextView;
    private EditText mLoginURL;
    private Button mLoginButton;
    private RequestQueue queue;
    private Base64 b64;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logintp);

        mUsernameTextView = findViewById(R.id.username);
        mLoginButton = findViewById(R.id.login);
        mLoginURL = findViewById(R.id.login_url);
        queue = Volley.newRequestQueue(this);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            private AuthClass[] accounts = authData.getAuthData();
            private AuthClass user;
            @Override
            public void onClick(View view) {
                //Check IP Adress
                String url = mLoginURL.getText().toString();
                if (url.isEmpty() || url.equals("")){
                    Toast.makeText(LoginActivity.this, "Ip Adress is required", Toast.LENGTH_SHORT).show();
                } else {
                    url = "http://" + mLoginURL.getText().toString() + ":3000/api/auth/login/java";
                }

                for (int i = 0; i < accounts.length; i++) {
                    String username = this.accounts[i].getUsername();

                    // Setup json object for departure
                    JSONObject jsonBody = new JSONObject();

                    if(mUsernameTextView.getText().toString().equals(username)){
                        user = accounts[i];
                        try {
                            jsonBody.put("name", mUsernameTextView.getText().toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Log.d("TAG_R", url);
                        Log.d("TAG_R", jsonBody.toString());

                        // Enter the correct url for your api service site
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        Toast.makeText(LoginActivity.this,"Logged In",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        intent.putExtra("currentUsername", user.getUsername());
                                        intent.putExtra("ipUrl", mLoginURL.getText().toString());
                                        startActivity(intent);
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(LoginActivity.this,"Username and Private key combination is Incorrect",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                        //Add the request to the RequestQueue.
                        queue.add(jsonObjectRequest);
                    }
                }
            }
        });
    }
}

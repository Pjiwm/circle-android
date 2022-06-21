package com.pedro.rtpstreamer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.pedro.rtpstreamer.utils.GlobalVar;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    AuthData authData = new AuthData();
    private EditText mUsernameTextView;
    private Button mLoginButton;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logintp);

        mUsernameTextView = findViewById(R.id.username);
        mLoginButton = findViewById(R.id.login);
        queue = Volley.newRequestQueue(this);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            private AuthClass[] accounts = authData.getAuthData();
            private AuthClass user;
            private MainActivity mainContext = new MainActivity();
            private GlobalVar globalVar;
            @Override
            public void onClick(View view) {
                for (int i = 0; i < accounts.length; i++) {
                    String username = this.accounts[i].getUsername();

                    // Setup json object and url for departure
                    String url = "http://10.0.2.2:3000/api/auth/login";
                    JSONObject jsonBody = new JSONObject();
                    String hash = mainContext.sha256String(mUsernameTextView.getText().toString());

                    if(mUsernameTextView.getText().toString().equals(username)){
                        user = accounts[i];
                        try {
                            String signature = mainContext.encrypt(hash, user);
                            jsonBody.put("name", mUsernameTextView.getText().toString());
                            jsonBody.put("signature", signature);
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
                                        Toast.makeText(LoginActivity.this,"Username and Private key combination is Correct",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(LoginActivity.this,"Username and Private key combination is Incorrect",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                                AuthClass s = ((GlobalVar) MainActivity.this.getApplication()).getCurrentUser();
                                intent.putExtra("currentUsername", user.getUsername());
                                Log.d("TAG_USER", user.getUsername());
                                startActivity(intent);
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

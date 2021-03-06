/*
 * Copyright (C) 2021 pedroSG94.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pedro.rtpstreamer;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.pedro.encoder.input.video.CameraHelper;
import com.pedro.encoder.input.video.CameraOpenException;
import com.pedro.rtmp.utils.ConnectCheckerRtmp;
import com.pedro.rtplibrary.rtmp.RtmpCamera1;
import com.pedro.rtpstreamer.R;
import com.pedro.rtpstreamer.utils.AuthClass;
import com.pedro.rtpstreamer.utils.AuthData;
import com.pedro.rtpstreamer.utils.KeyUtils;
import com.pedro.rtpstreamer.utils.KeyUtilsDemo;
import com.pedro.rtpstreamer.utils.PathUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.StringReader;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Locale;
import javax.crypto.Cipher;

/**
 * More documentation see:
 * {@link com.pedro.rtplibrary.base.Camera1Base}
 * {@link com.pedro.rtplibrary.rtmp.RtmpCamera1}
 */
public class MainActivity extends AppCompatActivity
        implements Button.OnClickListener, ConnectCheckerRtmp, SurfaceHolder.Callback,
        View.OnTouchListener {

  private static final String LOG_TAG = "Log: ";
  AuthData authData = new AuthData();
  private Integer[] orientations = new Integer[] { 0, 90, 180, 270 };
  private AuthClass[] accounts = authData.getAuthData();
  private AuthClass currentUser = null;
  private String currentUsername = null;
  private String ipUrl = null;
  private Base64 b64;
  private RtmpCamera1 rtmpCamera1;
  private Button bStartStop, bRecord;
  private EditText etMessage;
  private String currentDateAndTime = "";
  private File folder;
  //options menu
  private DrawerLayout drawerLayout;
  private NavigationView navigationView;
  private ActionBarDrawerToggle actionBarDrawerToggle;
  private RadioGroup rgChannel;
  private Spinner spResolution;
  private CheckBox cbEchoCanceler, cbNoiseSuppressor;
  private EditText etVideoBitrate, etFps, etAudioBitrate, etSampleRate, etWowzaUser,
          etWowzaPassword;
  private String lastVideoBitrate;
  private TextView tvBitrate;
  private TextView mChatTextView;
  private ScrollView mChatScrollView;
  private String serverKey = "-----BEGIN PUBLIC KEY-----MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCvRD121HacefHEo5xRGFsCUzrsRtyeQ5Xs8ZVPu2nbpoPGSZqFbInC+bh9vwGkX0yl9o2FTV/IVUG7SQAXf2MulPB6lPRX8L7BCDkitgkXrW6Mym41fm139CVJVjLt/djVuJ3/QifpM0O5GQeBr20EJgCvAkhBZZt7U+1P6gxZdwIDAQAB-----END PUBLIC KEY-----";
  private JSONArray uuids = new JSONArray();
  private RequestQueue queue;
  private Context mContext;
  private
  final Timer timer = new Timer();


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    setContentView(R.layout.activity_custom);
    folder = PathUtils.getRecordPath();
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);

    SurfaceView surfaceView = findViewById(R.id.surfaceView);
    surfaceView.getHolder().addCallback(this);
    surfaceView.setOnTouchListener(this);
    checkAndRequestPermissions();
      rtmpCamera1 = new RtmpCamera1(surfaceView, this);
    prepareOptionsMenuViews();
    tvBitrate = findViewById(R.id.tv_bitrate);
    etMessage = findViewById(R.id.send_text_message);
    etMessage.setHint(R.string.hint_chat);
    bStartStop = findViewById(R.id.b_start_stop);
    bStartStop.setOnClickListener(this);
    bRecord = findViewById(R.id.b_record);
    bRecord.setOnClickListener(this);
    Button switchCamera = findViewById(R.id.switch_camera);
    switchCamera.setOnClickListener(this);
    mChatTextView = findViewById(R.id.chat_textView);
    mChatScrollView = findViewById(R.id.chat_scrollview);
    queue = Volley.newRequestQueue(this);
    Intent intent = getIntent();
    if(intent.hasExtra("currentUsername")){
      currentUsername = intent.getExtras().getString("currentUsername");
      for (int i = 0; i < this.accounts.length; i++) {
        System.out.println(this.accounts[i].getUsername());
        if(currentUsername.equals(accounts[i].getUsername())){
          currentUser = accounts[i];
        }
      }
    }
    if(intent.hasExtra("ipUrl")) {
      ipUrl = intent.getExtras().getString("ipUrl");
    }
    System.out.println();
//    KeyUtilsDemo.jsDemo();
  }

  private void checkAndRequestPermissions() {
    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};

    if (!hasPermissions(this, PERMISSIONS)) {
      ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
    }
  }

  public static boolean hasPermissions(Context context, String... permissions) {
    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
      for (String permission : permissions) {
        if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
          return false;
        }
      }
    }
    return true;
  }



  private void prepareOptionsMenuViews() {
    drawerLayout = findViewById(R.id.activity_custom);
    navigationView = findViewById(R.id.nv_rtp);

    navigationView.inflateMenu(R.menu.options_rtmp);
    actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.rtmp_streamer,
            R.string.rtmp_streamer) {

      public void onDrawerOpened(View drawerView) {
        actionBarDrawerToggle.syncState();
        lastVideoBitrate = etVideoBitrate.getText().toString();
      }

      public void onDrawerClosed(View view) {
        actionBarDrawerToggle.syncState();
        if (lastVideoBitrate != null && !lastVideoBitrate.equals(
                etVideoBitrate.getText().toString()) && rtmpCamera1.isStreaming()) {
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int bitrate = Integer.parseInt(etVideoBitrate.getText().toString()) * 1024;
            rtmpCamera1.setVideoBitrateOnFly(bitrate);
            Toast.makeText(MainActivity.this, "New bitrate: " + bitrate, Toast.LENGTH_SHORT).
                    show();
          } else {
            Toast.makeText(MainActivity.this, "Bitrate on fly ignored, Required min API 19",
                    Toast.LENGTH_SHORT).show();
          }
        }
      }
    };
    drawerLayout.addDrawerListener(actionBarDrawerToggle);
    //checkboxs
    cbEchoCanceler =
            (CheckBox) navigationView.getMenu().findItem(R.id.cb_echo_canceler).getActionView();
    cbNoiseSuppressor =
            (CheckBox) navigationView.getMenu().findItem(R.id.cb_noise_suppressor).getActionView();
    //radiobuttons
    RadioButton rbTcp =
            (RadioButton) navigationView.getMenu().findItem(R.id.rb_tcp).getActionView();
    rgChannel = (RadioGroup) navigationView.getMenu().findItem(R.id.channel).getActionView();
    rbTcp.setChecked(true);
    //spinners
    spResolution = (Spinner) navigationView.getMenu().findItem(R.id.sp_resolution).getActionView();

    ArrayAdapter<Integer> orientationAdapter =
            new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item);
    orientationAdapter.addAll(orientations);

    ArrayAdapter<String> resolutionAdapter =
            new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item);
    List<String> list = new ArrayList<>();
    for (Camera.Size size : rtmpCamera1.getResolutionsBack()) {
      list.add(size.width + "X" + size.height);
    }
    resolutionAdapter.addAll(list);
    spResolution.setAdapter(resolutionAdapter);
    //edittexts
    etVideoBitrate =
            (EditText) navigationView.getMenu().findItem(R.id.et_video_bitrate).getActionView();
    etFps = (EditText) navigationView.getMenu().findItem(R.id.et_fps).getActionView();
    etAudioBitrate =
            (EditText) navigationView.getMenu().findItem(R.id.et_audio_bitrate).getActionView();
    etSampleRate = (EditText) navigationView.getMenu().findItem(R.id.et_samplerate).getActionView();
    etVideoBitrate.setText("2500");
    etFps.setText("30");
    etAudioBitrate.setText("128");
    etSampleRate.setText("44100");
    etWowzaUser = (EditText) navigationView.getMenu().findItem(R.id.et_user).getActionView();
    etWowzaPassword =
            (EditText) navigationView.getMenu().findItem(R.id.et_password).getActionView();
  }

  @Override
  protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    actionBarDrawerToggle.syncState();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
          drawerLayout.openDrawer(GravityCompat.START);
        } else {
          drawerLayout.closeDrawer(GravityCompat.START);
        }
        return true;
      case R.id.microphone:
        if (!rtmpCamera1.isAudioMuted()) {
          item.setIcon(getResources().getDrawable(R.drawable.icon_microphone_off));
          rtmpCamera1.disableAudio();
        } else {
          item.setIcon(getResources().getDrawable(R.drawable.icon_microphone));
          rtmpCamera1.enableAudio();
        }
        return true;
        case R.id.loginmenu:
          Intent intent = new Intent(this, LoginActivity.class);
          startActivity(intent);
          return true;

      default:
        return false;
    }
  }

  public void getChat() {
    String url = "http://10.0.2.2:3000/api/rooms/" + currentUser.getRoomId() + "/chats";
    if (ipUrl.isEmpty() || ipUrl.equals("")) {
      Toast.makeText(mContext, "No URL defined", Toast.LENGTH_SHORT);
    } else {
      url = "http://" + ipUrl + ":3000/api/rooms/" + currentUser.getRoomId() + "/chats";
    }
    mChatTextView.setText(null);
    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
            new Response.Listener<JSONObject>() {
              @Override
              public void onResponse(JSONObject response) {
                try {
                  JSONObject chatsObject =new JSONObject();
                  JSONArray chats = response.getJSONArray("chats");
                  chatsObject.put("roomChats",chats);
                  //Decryption voor de lijst van messages.
                  String chatsSignature = response.getString("signature");

                  Log.d("TAG_D", chatsSignature);
                  String uuid = KeyUtils.decrypt(chatsSignature, KeyUtils.jsonObjectToByteArray(chatsObject), KeyUtils.stringToPublicKey(serverKey));
                  Log.d("TAG_D", chatsObject.toString());
                  if(uuid != null) {
                    if (!uuid.isEmpty() || !uuid.equals("")) {
                      Date uuidDate = new Date();
                      JSONObject uuidObject = new JSONObject();
                      uuidObject.put("uuid", uuid);
                      uuidObject.put("date", uuidDate);
                      boolean wrongMessage = false;
                      for (int k = 0; k < uuids.length(); k++) {
                        if (uuidObject.getString("uuid").equals(uuids.getJSONObject(k).getString("uuid"))) {
                          wrongMessage = true;
                        }
                      }
                      if (!wrongMessage) {
                        uuids.put(uuidObject);
                      } else {
                        Toast.makeText(mContext, "Incorrect Message Received", Toast.LENGTH_SHORT);
                        return;
                      }
                    }
                  }
                  for (int i = 0; i < chats.length(); i++) {
                    JSONObject chatMessage = chats.getJSONObject(i);
                    JSONObject person = chatMessage.getJSONObject("person");
                    Log.d("TAG_D", String.valueOf(chats.length()) + " " + String.valueOf(i));

                    for(int j = 0; j < accounts.length; j++) {
                      Log.d("TAG_D", person.getString("_id") + " " + accounts[j].getPersonId());
                      if (person.getString("_id").equals(accounts[j].getPersonId())) {
                        String message = chatMessage.getString("message");
                        mChatTextView.append(accounts[j].getUsername() + ": " + message + "\n\n");

                          mChatScrollView.post(new Runnable() {
                            @Override
                            public void run() {
                              mChatScrollView.fullScroll(View.FOCUS_DOWN);
                            }
                          });
                      }
                    }
                  }
                } catch (JSONException e) {
                  e.printStackTrace();
                } catch (Exception e) {
                  e.printStackTrace();
                }
              }
            }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        error.printStackTrace();
      }
    });

    queue.add(request);
  }

  public void updateUuidList() {
    try {
      uuids = renewUuidList(uuids);
    } catch (JSONException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  public static JSONArray renewUuidList(JSONArray uuids) throws JSONException, ParseException {
    JSONArray newJsonArray = new JSONArray();
    for(int i = 0; i < uuids.length(); i++) {
      JSONObject obj = uuids.getJSONObject(i);
      String dateStr = obj.getString("date");
      Log.i("DateString", dateStr);
      SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy");
      Date date = sdf.parse(dateStr);
      Date death = addSecondsToDate(new Date(), -30);
      if(!date.before(death)) {
        newJsonArray.put(uuids.getJSONObject(i));
      }
    }
    return newJsonArray;
  }

  public static Date addSecondsToDate(Date date, int seconds) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.SECOND, seconds);
    return calendar.getTime();
  }

  Handler mHandler = new Handler();
  Runnable mHandlerTask = new Runnable()
  {
    @Override
    public void run() {
      getChat();
      updateUuidList();
      mHandler.postDelayed(mHandlerTask, 10000);
    }
  };

  void startRepeatingTask()
  {
    mHandlerTask.run();
  }

  void stopRepeatingTask()
  {
    mHandler.removeCallbacks(mHandlerTask);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.b_start_stop:
        if (currentUser != null) {
          Log.d("TAG_R", "b_start_stop: ");
          if (!rtmpCamera1.isStreaming()) {
            bStartStop.setText(getResources().getString(R.string.stop_button));
            String user = etWowzaUser.getText().toString();
            String password = etWowzaPassword.getText().toString();
            if (!user.isEmpty() && !password.isEmpty()) {
              rtmpCamera1.setAuthorization(user, password);
            }
            if (rtmpCamera1.isRecording() || prepareEncoders()) {
              String url = "rtmp://10.0.2.2/live/person";
              if (ipUrl.isEmpty() || ipUrl.equals("")) {
                Toast.makeText(mContext, "No URL defined", Toast.LENGTH_SHORT);
              } else {
                url = "rtmp://" + ipUrl + "/live/" + currentUser.getUsername();
              }
              Log.d("TAG_D", url);
              rtmpCamera1.startStream(url);

              //If you get through starting the stream, your chat will start loading
              startRepeatingTask();

            } else {
              //If you see this all time when you start stream,
              //it is because your encoder device dont support the configuration
              //in video encoder maybe color format.
              //If you have more encoder go to VideoEncoder or AudioEncoder class,
              //change encoder and try
              Toast.makeText(this, "Error preparing stream, This device cant do it",
                      Toast.LENGTH_SHORT).show();
              bStartStop.setText(getResources().getString(R.string.start_button));
              stopRepeatingTask();
            }
          } else {
            bStartStop.setText(getResources().getString(R.string.start_button));
            rtmpCamera1.stopStream();
            timer.cancel();
          }
        } else {
          Toast.makeText(this, "Not logged in", Toast.LENGTH_SHORT).show();
        }
        break;
      case R.id.b_record:
        Log.d("TAG_R", "send message");

        // Setup json object and url for departure
        String url = "http://10.0.2.2:3000/api/chats/java";
        if (ipUrl.isEmpty() || ipUrl.equals("")) {
          Toast.makeText(mContext, "No URL defined", Toast.LENGTH_SHORT);
        } else {
          url = "http://" + ipUrl + ":3000/api/chats/java";
        }
        JSONObject jsonBody = new JSONObject();
        try {

          jsonBody.put("person", new String(currentUser.getPersonId()));
          jsonBody.put("room", currentUser.getRoomId());
          jsonBody.put("message", etMessage.getText().toString());
          jsonBody.put("dateTime", new Date());
          jsonBody.put("signature", "xxx");
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
                    Toast.makeText(MainActivity.this, "Message sent", Toast.LENGTH_SHORT).show();
                    etMessage.setText("");
                    getChat();
                  }
                }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            Toast.makeText(MainActivity.this, "Error in sending data to API", Toast.LENGTH_SHORT).show();
          }
        });

        //Add the request to the RequestQueue.
        this.queue.add(jsonObjectRequest);
        break;
      case R.id.switch_camera:
        try {
          rtmpCamera1.switchCamera();
        } catch (final CameraOpenException e) {
          Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        break;
      default:
        break;
    }
  }

  private boolean prepareEncoders() {
    Camera.Size resolution =
            rtmpCamera1.getResolutionsBack().get(spResolution.getSelectedItemPosition());
    int width = resolution.width;
    int height = resolution.height;
    return rtmpCamera1.prepareVideo(width, height, Integer.parseInt(etFps.getText().toString()),
            Integer.parseInt(etVideoBitrate.getText().toString()) * 1024,
            CameraHelper.getCameraOrientation(this)) && rtmpCamera1.prepareAudio(
            Integer.parseInt(etAudioBitrate.getText().toString()) * 1024,
            Integer.parseInt(etSampleRate.getText().toString()),
            rgChannel.getCheckedRadioButtonId() == R.id.rb_stereo, cbEchoCanceler.isChecked(),
            cbNoiseSuppressor.isChecked());
  }

  @Override
  public void onConnectionStartedRtmp(String rtmpUrl) {
  }

  @Override
  public void onConnectionSuccessRtmp() {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        Toast.makeText(MainActivity.this, "Connection success", Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Override
  public void onConnectionFailedRtmp(final String reason) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        Toast.makeText(MainActivity.this, "Connection failed. " + reason, Toast.LENGTH_SHORT)
                .show();
        rtmpCamera1.stopStream();
        bStartStop.setText(getResources().getString(R.string.start_button));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2
                && rtmpCamera1.isRecording()) {
          rtmpCamera1.stopRecord();
          PathUtils.updateGallery(getApplicationContext(), folder.getAbsolutePath() + "/" + currentDateAndTime + ".mp4");
          bRecord.setText(R.string.send_message);
          Toast.makeText(MainActivity.this,
                  "file " + currentDateAndTime + ".mp4 saved in " + folder.getAbsolutePath(),
                  Toast.LENGTH_SHORT).show();
          currentDateAndTime = "";
        }
      }
    });
  }

  @Override
  public void onNewBitrateRtmp(final long bitrate) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        tvBitrate.setText(bitrate + " bps");
      }
    });
  }

  @Override
  public void onDisconnectRtmp() {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        Toast.makeText(MainActivity.this, "Disconnected", Toast.LENGTH_SHORT).show();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2
                && rtmpCamera1.isRecording()) {
          rtmpCamera1.stopRecord();
          PathUtils.updateGallery(getApplicationContext(), folder.getAbsolutePath() + "/" + currentDateAndTime + ".mp4");
          bRecord.setText(R.string.send_message);
          Toast.makeText(MainActivity.this,
                  "file " + currentDateAndTime + ".mp4 saved in " + folder.getAbsolutePath(),
                  Toast.LENGTH_SHORT).show();
          currentDateAndTime = "";
        }
      }
    });
  }

  @Override
  public void onAuthErrorRtmp() {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        Toast.makeText(MainActivity.this, "Auth error", Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Override
  public void onAuthSuccessRtmp() {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        Toast.makeText(MainActivity.this, "Auth success", Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Override
  public void surfaceCreated(SurfaceHolder surfaceHolder) {
    //drawerLayout.openDrawer(GravityCompat.START);
  }

  @Override
  public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    rtmpCamera1.startPreview();
    // optionally:
    //rtmpCamera1.startPreview(CameraHelper.Facing.BACK);
    //or
    //rtmpCamera1.startPreview(CameraHelper.Facing.FRONT);
  }

  @Override
  public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2 && rtmpCamera1.isRecording()) {
      rtmpCamera1.stopRecord();
      PathUtils.updateGallery(this, folder.getAbsolutePath() + "/" + currentDateAndTime + ".mp4");
      bRecord.setText(R.string.send_message);
      Toast.makeText(this,
              "file " + currentDateAndTime + ".mp4 saved in " + folder.getAbsolutePath(),
              Toast.LENGTH_SHORT).show();
      currentDateAndTime = "";
    }
    if (rtmpCamera1.isStreaming()) {
      rtmpCamera1.stopStream();
      bStartStop.setText(getResources().getString(R.string.start_button));
    }
    rtmpCamera1.stopPreview();
  }

  @Override
  public boolean onTouch(View view, MotionEvent motionEvent) {
    int action = motionEvent.getAction();
    if (motionEvent.getPointerCount() > 1) {
      if (action == MotionEvent.ACTION_MOVE) {
        rtmpCamera1.setZoom(motionEvent);
      }
    } else if (action == MotionEvent.ACTION_DOWN) {
      rtmpCamera1.tapToFocus(view, motionEvent);
    }
    return true;
  }
}

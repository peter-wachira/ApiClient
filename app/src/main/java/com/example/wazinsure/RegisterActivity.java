package com.example.wazinsure;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.nameEditText) EditText mName;
    @BindView(R.id.idEditText) EditText mId;
    @BindView(R.id.numberEditText) EditText mNumber;
    @BindView(R.id.emailEditText) EditText mEmail;
    @BindView(R.id.profileEditText) EditText mProfile;
    @BindView(R.id.usernameEditText) EditText mUserName;
    @BindView(R.id.passwordEditText) EditText mPassword;
    @BindView(R.id.already) TextView mAlready;
    @BindView(R.id.chap) TextView mChap;
    @BindView(R.id.ready) TextView mReady;
    @BindView(R.id.profileImage)
    ImageView _profileurlText;
    Uri resultUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        checkNetworkConnection();

        mReady.setOnClickListener(this);
        mChap.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        if(v == mReady) {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        if(v == mChap){
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
        }
        _profileurlText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseProfileImage();
            }
        });

    }


    //method for selecting image
    private void chooseProfileImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            final Uri imageUri = data.getData();
            resultUri = imageUri;
            _profileurlText.setImageURI(resultUri);
        }
    }



    public boolean checkNetworkConnection(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        boolean isConnected = false;

        if(networkInfo != null && (isConnected = networkInfo.isConnected())){
            mChap.setText("Network Established");
        } else {
            mChap.setText("Not Connected");
        }
        return isConnected;
    }
    private String httpPost(String myUrl) throws IOException, JSONException {
        String result = "";

        URL url = new URL(myUrl);

        // creation of HttpURLConnection
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

        // build JSON object, add JSON content to POST request body, make POST request to given URL return response message
        JSONObject jsonObject = buildJsonObject();
        setPostRequestContent(conn, jsonObject);

        conn.connect();
        return conn.getResponseMessage() + " " + "Submitted";
    }

    private class HTTPAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        // displaying result of AsyncTask
        protected String doInBackground(String... urls) {
            try {
                try {
                    return httpPost(urls[0]);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return "Failed";
                }
            } catch (IOException e) {
                return "Unable to complete your order";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            mAlready.setText(result);

            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

        public void send(View view){
            Toast.makeText(RegisterActivity.this, "Submitted", Toast.LENGTH_SHORT).show();

            if(checkNetworkConnection())
                new HTTPAsyncTask().execute("https://demo.wazinsure.com:4443/auth/register");
            else
                Toast.makeText(RegisterActivity.this,"Failed", Toast.LENGTH_SHORT).show();
        }

        private JSONObject buildJsonObject() throws JSONException{
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("fullname",mName.getText().toString());
            jsonObject.accumulate("id_no",mId.getText().toString());
            jsonObject.accumulate("mobile_no",mNumber.getText().toString());
            jsonObject.accumulate("email",mEmail.getText().toString());
            jsonObject.accumulate("profileurl",mProfile.getText().toString());
            jsonObject.accumulate("username",mUserName.getText().toString());
            jsonObject.accumulate("password",mPassword.getText().toString());

            return  jsonObject;
        }
        private void setPostRequestContent(HttpURLConnection conn, JSONObject jsonObject) throws IOException{
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            writer.write(jsonObject.toString());
            Log.i(RegisterActivity.class.toString(), jsonObject.toString());
            writer.flush();
            writer.close();
            os.close();
        }
    }


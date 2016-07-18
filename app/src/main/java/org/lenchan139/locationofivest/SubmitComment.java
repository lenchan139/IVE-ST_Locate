package org.lenchan139.locationofivest;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class SubmitComment extends AppCompatActivity {

    private EditText edtFirstName, edtLastName, edtEmail, edtComments;
    private Button   btnSend;
    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_comment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        edtFirstName = (EditText) findViewById(R.id.edtFirstName);
        edtEmail     = (EditText) findViewById(R.id.edtEmail);
        edtComments  = (EditText) findViewById(R.id.edtComments);

        btnSend = (Button) findViewById(R.id.btnSend);

        tvStatus = (TextView) findViewById(R.id.tvStatus);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                if( (edtComments.getText().toString().matches("") ) ||
                        (edtFirstName.getText().toString().matches("")||
                                (edtEmail.getText().toString().matches("")))){

                    Toast.makeText(SubmitComment.this, "Error! All fields should have contents!", Toast.LENGTH_LONG).show();

                }else{
                    final String url = "https://lenchan139.org/myWorks/ivest_Script/comments/insert.php";
                    new MyAsyncTask().execute(url);
                    onBackPressed();
                }
                //Intent intent = new Intent(SubmitComment.this, ShowAll.class);
                //startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SubmitComment.this, Comment.class);
        startActivity(intent);
        finishX();
    }

    public String executeHttpPost(String url) {
        String result = "";

        //HttpClient acts like a Browser (without the UI)
        HttpClient client = new DefaultHttpClient();

        // Create object to represent a POST request
        HttpPost request = new HttpPost(url);

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);

        //  $_POST[ ] values to PHP
        nameValuePairs.add(new BasicNameValuePair("FirstName", edtFirstName.getText().toString()));
        nameValuePairs.add(new BasicNameValuePair("LastName",  ""));
        nameValuePairs.add(new BasicNameValuePair("Email",     edtEmail.getText().toString()));
        nameValuePairs.add(new BasicNameValuePair("Comment",   edtComments.getText().toString()));

        // This will store the response from the server
        HttpResponse response;

        try {
            request.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Actually call the server
            response = client.execute(request);

            // Extract text message from server
            result = EntityUtils.toString(response.getEntity());

        } catch (Exception e) {
            result = "[ERROR] " + e.toString();

            Log.v("myLog", "result: " + result);
        }

        return result;
    }

    private class MyAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... url) {
            return executeHttpPost(url[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            tvStatus.setText(result);
        }
    }
    public void finishX(){
        overridePendingTransition(0, 0);
        finish();
    }
}  // end of MainActivity








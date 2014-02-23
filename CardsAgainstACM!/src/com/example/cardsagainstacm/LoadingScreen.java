package com.example.cardsagainstacm;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.view.Menu;
import android.widget.TextView;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class LoadingScreen extends Activity {

    private Client client;

    private static final int SERVERPORT = 6969;
    private static final String SERVER_IP = "10.18.83.26";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        StrictMode.ThreadPolicy policy = new StrictMode.
        ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

    public void connect(View view){
        //EditText ed = (EditText)findViewById(R.id.editText);
        new Thread(client = new Client("10.18.83.26")).start();

    }

    public void onClick(View view) {


        TextView tv = (TextView) findViewById(R.id.textView2);
        String msg;
            if( (msg = client.getInput()) != null){
                tv.setText(msg);
            }
    }

}
    


package com.example.cardsagainstacm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.view.Menu;

public class LoadingScreen extends Activity {

    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
    }

    public void connect(View view){
        //EditText ed = (EditText)findViewById(R.id.editText);
        new Thread(new Client("10.18.82.71")).start();
    }
    
}

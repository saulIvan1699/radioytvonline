package com.example.radiotvonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class am extends AppCompatActivity {

    String URL = "rtmp://s5.mexside.net:1935/radioam/radioam";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.am);

        //--------------------------------------------------------------------------------------------------------------
        //-----------------------------IN THIS SECTION IS THE BUTTON NAVIGATION-----------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        //Here is the code of how to navigate with buttons for the Main Activity button
        ImageView button_am = findViewById(R.id.btn_go_back_am);
        button_am.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start Activity, here we send the activity from where we are to where it will take us
                Intent intent = new Intent(am.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------


        //--------------------------------------------------------------------------------------------------------------
        //-----------------------------CODE TO UPDATE THE ACTIVITY WHERE IT IS LOCATED--------------------------------
        //--------------------------------------------------------------------------------------------------------------

        ImageView refreshButton = findViewById(R.id.img_refresh_am);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Here the current activity is restarted
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------

    }
}


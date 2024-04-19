package com.example.radiotvonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //--------------------------------------------------------------------------------------------------------------
        //-----------------------------CODE FOR THE VIDEO AT THE TOP TO PLAY AUTOMATICALLY------------------------------
        //--------------------------------------------------------------------------------------------------------------

        //In this part we have what is a code where we play a video which requires us to put an image
        //in the xml and with only the measurements and the id and here we only send it to call.
        VideoView videoView = findViewById(R.id.vv_test);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        videoView.setOnPreparedListener(MediaPlayer::start); // Start playback automatically
        videoView.setOnCompletionListener(MediaPlayer::start); // Play the video again when it ends

        //-------------------------------------------------------------------------------------------------------------------
        //-------------------------------------END OF THIS SECTION-----------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------


        //--------------------------------------------------------------------------------------------------------------
        //-----------------------------IN THIS SECTION IS THE BUTTON NAVIGATION-----------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        //Here is the code of how to navigate with buttons for the am button
        ImageView button_am = findViewById(R.id.btn_am);
        button_am.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start Activity, here we send the activity from where we are to where it will take us
                Intent intent = new Intent(MainActivity.this, am.class);
                startActivity(intent);
            }
        });

        //Here is the code of how to navigate with buttons for the tv button
        ImageView button_tv = findViewById(R.id.btn_tv);
        button_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start Activity, here we send the activity from where we are to where it will take us
                Intent intent = new Intent(MainActivity.this, tv.class);
                startActivity(intent);
            }
        });

        //Here is the code of how to navigate with buttons for the fm button
        ImageView button_fm = findViewById(R.id.btn_fm);
        button_fm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start Activity, here we send the activity from where we are to where it will take us
                Intent intent = new Intent(MainActivity.this, fm.class);
                startActivity(intent);
            }
        });

        //-------------------------------------------------------------------------------------------------------------------
        //----------------------------------------END OF THIS SECTION--------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------


    }
}
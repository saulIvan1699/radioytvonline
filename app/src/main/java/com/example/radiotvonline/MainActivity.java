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

        //IN THIS PART WE HAVE WHAT IS A CODE WHERE WE PLAY A VIDEO WHICH REQUIRES US TO PUT AN IMAGE
        //IN THE XML AND WITH ONLY THE MEASUREMENTS AND THE ID AND HERE WE ONLY SEND IT TO CALL.
        VideoView videoView = findViewById(R.id.vv_test);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                float volume = 0.1f; // SET THE VOLUME TO 50% OF MAXIMUM (0.1)
                mp.setVolume(volume, volume); // SET THE VOLUME OF THE MEDIA PLAYER
                videoView.start(); // START PLAYBACK AUTOMATICALLY WHEN READY
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.start(); // PLAY THE VIDEO AGAIN WHEN IT FINISHES
            }
        });
        //-------------------------------------------------------------------------------------------------------------------
        //-------------------------------------END OF THIS SECTION-----------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------


        //--------------------------------------------------------------------------------------------------------------
        //-----------------------------IN THIS SECTION IS THE BUTTON NAVIGATION-----------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        //HERE IS THE CODE OF HOW TO NAVIGATE WITH BUTTONS FOR THE AM BUTTON
        ImageView button_am = findViewById(R.id.btn_am);
        button_am.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // CREATE AN INTENT TO START ACTIVITY, HERE WE SEND THE ACTIVITY FROM WHERE WE ARE TO WHERE IT WILL TAKE US
                Intent intent = new Intent(MainActivity.this, am.class);
                startActivity(intent);
            }
        });

        //HERE IS THE CODE OF HOW TO NAVIGATE WITH BUTTONS FOR THE TV BUTTON
        ImageView button_tv = findViewById(R.id.btn_tv);
        button_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // CREATE AN INTENT TO START ACTIVITY, HERE WE SEND THE ACTIVITY FROM WHERE WE ARE TO WHERE IT WILL TAKE US
                Intent intent = new Intent(MainActivity.this, tv.class);
                startActivity(intent);
            }
        });

        //HERE IS THE CODE OF HOW TO NAVIGATE WITH BUTTONS FOR THE TV BUTTON
        ImageView button_fm = findViewById(R.id.btn_fm);
        button_fm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // CREATE AN INTENT TO START ACTIVITY, HERE WE SEND THE ACTIVITY FROM WHERE WE ARE TO WHERE IT WILL TAKE US
                Intent intent = new Intent(MainActivity.this, fm.class);
                startActivity(intent);
            }
        });

        //-------------------------------------------------------------------------------------------------------------------
        //----------------------------------------END OF THIS SECTION--------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------
    }
}
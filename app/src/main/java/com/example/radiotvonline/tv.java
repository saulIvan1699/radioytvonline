package com.example.radiotvonline;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.brightcove.player.analytics.Analytics;
import com.brightcove.player.model.DeliveryType;
import com.brightcove.player.model.Video;
import com.brightcove.player.view.BrightcoveExoPlayerVideoView;
import com.brightcove.player.view.BrightcovePlayer;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.BuildConfig;

import java.io.IOException;
import java.net.URISyntaxException;


public class tv extends AppCompatActivity {



    //String videoUrl = "rtmp://s5.mexside.net:1935/enlinea/enlinea";

    private String URL = "rtmp://s5.mexside.net:1935/enlinea/enlinea";
    private ProgressDialog dp;
    private BrightcoveExoPlayerVideoView videoView;
    private String videoUri = "rtmp://s5.mexside.net:1935/enlinea/enlinea";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tv);



        //rtmp://s5.mexside.net:1935/enlinea/enlinea


        //--------------------------------------------------------------------------------------------------------------
        //-----------------------------Aqui tenemos el codigo para transmitir video en vivo-----------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        //Aqui solo estoy declarando algubnas variables y mandando llamar una funcion que se llama playvideo
        videoView = findViewById(R.id.videoView);
        dp = new ProgressDialog(this);
        dp.setCancelable(true);

        playVideo();

    //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------------------------------
        //-----------------------------IN THIS SECTION IS THE BUTTON NAVIGATION-----------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        //Here is the code of how to navigate with buttons for the Main Activity button
        ImageView button_tv = findViewById(R.id.btn_go_back_tv);
        button_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start Activity, here we send the activity from where we are to where it will take us
                Intent intent = new Intent(tv.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------------------------------
        //-----------------------------CODE TO UPDATE THE ACTIVITY WHERE IT IS LOCATED--------------------------------
        //--------------------------------------------------------------------------------------------------------------

        ImageView refreshButton = findViewById(R.id.img_refresh_tv);
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

//aquib tenemos lo que es la reproduccion del video en vivo
    private void playVideo() {
        try {
            getWindow().setFormat(PixelFormat.TRANSLUCENT);
            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);

            Uri videoUri = Uri.parse(URL);

            videoView.setMediaController(mediaController);
            videoView.setVideoURI(videoUri);
            videoView.requestFocus();

            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    dp.dismiss();
                    videoView.start();
                }
            });

        } catch (Exception e) {
            dp.dismiss();
            Toast.makeText(this, "Aqui no esta corriendo nada" , Toast.LENGTH_SHORT).show();
        }
    }
}
package com.example.radiotvonline;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;



public class tv extends AppCompatActivity {

    /*Televisión
    https://s5.mexside.net:1936/enlinea/enlinea/playlist.m3u8*/

    //DECLARATION OF GLOBAL VARIABLE OF THE MEDIA PLAYER
    private MediaPlayer mediaPlayer;

    //DECLARATION OF GLOBAL VARIABLE SEEKBAR
    private SeekBar volumeSeekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tv);

        // WE INITIALIZE MEDIA PLAYER
        mediaPlayer = new MediaPlayer();


        //https://s5.mexside.net:1936/enlinea/enlinea/playlist.m3u8
        //--------------------------------------------------------------------------------------------------------------
        //-----------------------------HERE WE HAVE THE CODE TO TRANSMIT LIVE TV----------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        VideoView videoView = findViewById(R.id.videoView); //THE ID IS ASSIGNED TO THE GLOBAL VARIABLE WE CREATE
        String URL = "https://s5.mexside.net:1936/enlinea/enlinea/playlist.m3u8";//WE DECLARE THE LIVE VIDEO URL

        //WE ASSIGN THE URL AS IF IT WERE A STRING
        Uri uri = Uri.parse(URL);
        //WE ASSIGN IT TO VIDEOVIEW
        videoView.setVideoURI(uri);

        //WE INITIALIZE IN ANDROID STUDIO PLAYER
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        //WE START PLAYING THE LIVE VIDEO
        videoView.start();

        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------END OF THIS SECTION-----------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------------------------------
        //-----------------------------HERE WE HAVE THE CODE TO CONTROL THE VOLUME---------------------------------
        //--------------------------------------------------------------------------------------------------------------

        // WE ASSIGN THE ID OF OUR SEEKBAR FROM OUR XML TO OUR GLOBAL VARIABLE
        volumeSeekBar = findViewById(R.id.seekbar_tv);
        //HERE WE HAVE THE CODE WHERE WE PREPARE THE SEEKBAR READY TO USE
        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mediaPlayer.isPlaying()) {
                    float volume = (float) (1 - (Math.log(100 - progress) / Math.log(100)));
                    mediaPlayer.setVolume(volume, volume);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // HERE WE DO NOT NEED TO PUT SOMETHING SINCE WE WILL ONLY CONTROL THE VOLUME
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // HERE WE DO NOT NEED TO PUT SOMETHING SINCE WE WILL ONLY CONTROL THE VOLUME
            }
        });

        //WE PREPARE THE MEDIA PLAYER TO PLAY IT
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer = mp;
                float volume = (float) (1 - (Math.log(100 - volumeSeekBar.getProgress()) / Math.log(100)));
                mediaPlayer.setVolume(volume, volume);
            }
        });

        //--------------------------------------------------------------------------------------------------------------
        //------------------------------------END OF THIS SECTION-------------------------------------------------------
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
        //--------------------------------------END OF THIS SECTION-----------------------------------------------------
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
        //--------------------------------------END OF THIS SECTION-----------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------

    }
}
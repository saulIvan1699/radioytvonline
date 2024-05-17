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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;



public class tv extends AppCompatActivity {

    TextView text_volumen_tv;

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

        // ERROR HANDLER.
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                // WE SHOW AN ERROR MESSAGE.
                Toast.makeText(tv.this, "Error al reproducir el video", Toast.LENGTH_SHORT).show();
                return true; //WE INDICATE THAT WE HAVE SHOWN THE ERROR.
            }
        });

        //WE START PLAYING THE LIVE VIDEO
        videoView.start();




        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------END OF THIS SECTION-----------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------------------------------
        //-----------------------------HERE WE HAVE THE CODE TO CONTROL THE VOLUME---------------------------------
        //--------------------------------------------------------------------------------------------------------------

        // WE ASSIGN THE ID OF OUR SEEKBAR AND VOLUME FROM OUR XML TO OUR GLOBAL VARIABLE
        volumeSeekBar = findViewById(R.id.seekbar_tv);
        text_volumen_tv = findViewById(R.id.text_volumen_tv);
        //HERE WE HAVE THE CODE WHERE WE PREPARE THE SEEKBAR READY TO USE
        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mediaPlayer.isPlaying()) {
                    float volume = (float) (1 - (Math.log(100 - progress) / Math.log(100)));
                    mediaPlayer.setVolume(volume, volume);
                }

                //SHOWS WHAT VOLUME THE AUDIO IS AT
                text_volumen_tv.setVisibility(View.VISIBLE);
                text_volumen_tv.setText(progress+"/100");
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

        //HERE IS THE CODE OF HOW TO NAVIGATE WITH BUTTONS FOR THE MAIN ACTIVITY BUTTON
        ImageView button_tv = findViewById(R.id.btn_go_back_tv);
        button_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // CREATE AN INTENT TO START ACTIVITY, HERE WE SEND THE ACTIVITY FROM WHERE WE ARE TO WHERE IT WILL TAKE US
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
                // HERE THE CURRENT ACTIVITY IS RESTARTED
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------END OF THIS SECTION-----------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------

    }
    //--------------------------------------------------------------------------------------------------------------
    //-----------------------------THIS COGO IS PART OF THE LIVE PLAYBACK OF THE TV---------------------------------
    //--------------------------------------------------------------------------------------------------------------

    //HERE WE ARE SAYING TO DESTROY THE SCREEN IN CASE IT DOESN'T HAVE A SIGNAL
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    //--------------------------------------------------------------------------------------------------------------
    //--------------------------------------END OF THIS SECTION-----------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------
}
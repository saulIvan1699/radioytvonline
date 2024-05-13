package com.example.radiotvonline;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;


public class fm extends AppCompatActivity {

    TextView text_volumen_fm;

    //DECLARATION OF GLOBAL VARIABLE SEEKBAR
    private SeekBar volumeSeekBar;

    //DECLARATION OF GLOBAL VARIABLE OF THE MEDIA PLAYER
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fm);

        // WE INITIALIZE MEDIA PLAYER
        mediaPlayer = new MediaPlayer();

        //--------------------------------------------------------------------------------------------------------------
        //-----------------------------HERE WE HAVE THE CODE TO STREAM LIVE RADIO FM--------------------------------
        //--------------------------------------        ------------------------------------------------------------------------

        //HERE IS THE URL OF THE FM RADIO
        //https://s5.mexside.net:1936/smrtvfm/smrtvfm/playlist.m3u8


        //DECLARE THE URL WITH THE TRANSMISSION URL
        String videoUrl = "https://s5.mexside.net:1936/smrtvfm/smrtvfm/playlist.m3u8";
        // ATTRIBUTES ARE PUT TO THE MEDIA PLAYER THAT WE INITIALIZE
        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build());

        //EN ESTA PARTE REDUSIMOS EL RIEGO DE QUE SE SALGA LA APP EN DADO DE DAR UN ERROR.
        try {
            mediaPlayer.setDataSource(videoUrl);
            mediaPlayer.prepare(); // YOU CAN LAUNCH THE ---->>CATCH<<----
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al reproducir audio"+ e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        //--------------------------------------------------------------------------------------------------------------
        //-----------------------------------------END OF THIS SECTION--------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------------------------------
        //----------------------------HERE WE HAVE THE CODE TO CONTROL THE VOLUME---------------------------------------
        //--------------------------------------------------------------------------------------------------------------
        // WE ASSIGN THE ID OF OUR SEEKBAR AND VOLUME FROM OUR XML TO OUR GLOBAL VARIABLE
        volumeSeekBar = findViewById(R.id.seekbar_fm);
        text_volumen_fm = findViewById(R.id.text_volumen_fm);

        // WE ESTABLISH THAT THE SEEKBAR INITIALIZES AT ZERO
        volumeSeekBar.setProgress(0);

        //HERE WE HAVE THE SETTINGS TO CONTROL THE VOLUME
        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float volume = (progress / 100f) * 4f; // CONVERT PROGRESS INTO A VALUE BETWEEN 0.0 y 1.0 AND WE INCREASE THE VOLUME
                mediaPlayer.setVolume(volume, volume);

                //SHOWS WHAT VOLUME THE AUDIO IS AT
                text_volumen_fm.setVisibility(View.VISIBLE);
                text_volumen_fm.setText(progress+"/100");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // HERE THERE IS NO PROBLEM THAT IT IS EMPTY SINCE WE ONLY NEED IT TO CONTROL THE AUDIO
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // HERE THERE IS NO PROBLEM THAT IT IS EMPTY SINCE WE ONLY NEED IT TO CONTROL THE AUDIO
            }
        });

        // WE SET THAT THE AUDIO VOLUME WILL INITIALIZE AT ZERO.
        mediaPlayer.setVolume(0, 0);
        //--------------------------------------------------------------------------------------------------------------
        //---------------------------------END OF THIS SECTION----------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------------------------------
        //-----------------------------CODE FOR THE VIDEO AT THE TOP TO PLAY AUTOMATICALLY------------------------------
        //--------------------------------------------------------------------------------------------------------------

        //IN THIS PART WE HAVE WHAT IS A CODE WHERE WE PLAY A VIDEO WHICH REQUIRES US TO PUT AN IMAGE
        //IN THE XML AND WITH ONLY THE MEASUREMENTS AND THE ID AND HERE WE ONLY SEND IT TO CALL.
        VideoView videoView = findViewById(R.id.onda_fm);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.onda;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        videoView.setOnPreparedListener(MediaPlayer::start); // START PLAYBACK AUTOMATICALLY
        videoView.setOnCompletionListener(MediaPlayer::start); // PLAY THE VIDEO AGAIN WHEN IT ENDS

        //-------------------------------------------------------------------------------------------------------------------
        //-------------------------------------END OF THIS SECTION-----------------------------------------------------------
        //-------------------------------------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------------------------------
        //-----------------------------IN THIS SECTION IS THE BUTTON NAVIGATION-----------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        //HERE IS THE CODE OF HOW TO NAVIGATE WITH BUTTONS FOR THE MAIN ACTIVITY BUTTON
        ImageView button_fm = findViewById(R.id.btn_go_back_fm);
        button_fm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // CREATE AN INTENT TO START ACTIVITY, HERE WE SEND THE ACTIVITY FROM WHERE WE ARE TO WHERE IT WILL TAKE US
                Intent intent = new Intent(fm.this, MainActivity.class);
                startActivity(intent);
                mediaPlayer.pause();
            }
        });

        //--------------------------------------------------------------------------------------------------------------
        //---------------------------------------END OF THIS SECTION----------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------------------------------
        //-----------------------------CODE TO PAUSE AND PLAY AUDIO-----------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------
        ImageView ButtonPlayFm = findViewById(R.id.img_play_fm);

        ButtonPlayFm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });

        //----------------------------------------------------------------------------------------------------------------

        ImageView ButonPauseFm = findViewById(R.id.img_pause_fm);

        ButonPauseFm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
            }
        });

        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------END OF THIS SECTION-----------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------------------------------
        //-----------------------------CODE TO UPDATE THE ACTIVITY WHERE IT IS LOCATED--------------------------------
        //--------------------------------------------------------------------------------------------------------------

        ImageView refreshButton = findViewById(R.id.img_refresh_fm);
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
    //-----------------------------THIS COGO IS PART OF THE LIVE PLAYBACK OF THE FM RADIO---------------------------
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

    //-------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------END OF THIS SECTION-----------------------------------------------------
    //-------------------------------------------------------------------------------------------------------------------
}
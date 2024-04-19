package com.example.radiotvonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;


public class am extends AppCompatActivity {

    //DECLARATION OF GLOBAL VARIABLE SEEKBAR
    private SeekBar volumeSeekBar;

    //DECLARATION OF GLOBAL VARIABLE OF THE MEDIA PLAYER
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.am);

        // WE INITIALIZE MEDIA PLAYER
        mediaPlayer = new MediaPlayer();

        //--------------------------------------------------------------------------------------------------------------
        //-----------------------------HERE WE HAVE THE CODE TO STREAM LIVE RADIO FM------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        //HERE IS THE URL OF THE AM RADIO
        //https://s5.mexside.net:1936/radioam/radioam/playlist.m3u8

        //DECLARE THE URL WITH THE TRANSMISSION URL
        String videoUrl = "https://s5.mexside.net:1936/radioam/radioam/playlist.m3u8";
        // ATTRIBUTES ARE PUT TO THE MEDIA PLAYER THAT WE INITIALIZE
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build());

        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        //IN THIS PART WE REDUCE THE RISK OF THE APP EXIT IN CASE OF AN ERROR.
        try {
            mediaPlayer.setDataSource(videoUrl);
            mediaPlayer.prepare(); // Puede lanzar IOException
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al reproducir audio", Toast.LENGTH_SHORT).show();
        }


        //--------------------------------------------------------------------------------------------------------------
        //------------------------------------END OF THIS SECTION-------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------------------------------
        //-----------------------------HERE WE HAVE THE CODE TO CONTROL THE VOLUME--------------------------------------
        //--------------------------------------------------------------------------------------------------------------


        // WE ASSIGN THE ID TO OUR VARIABLE THAT WE DECLARE GLOBALLY
        volumeSeekBar = findViewById(R.id.seekbar_am);
        //HERE WE HAVE THE SETTINGS TO CONTROL THE VOLUME
        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float volume = progress / 100f; // // CONVERT PROGRESS INTO A VALUE BETWEEN 0.0 y 1.0
                mediaPlayer.setVolume(volume, volume);
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

        //--------------------------------------------------------------------------------------------------------------
        //------------------------------------END OF THIS SECTION-------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------------------------------
        //-----------------------------CODE FOR THE VIDEO AT THE TOP TO PLAY AUTOMATICALLY------------------------------
        //--------------------------------------------------------------------------------------------------------------

        //In this part we have what is a code where we play a video which requires us to put an image
        //in the xml and with only the measurements and the id and here we only send it to call.
        VideoView videoView = findViewById(R.id.onda_am);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.onda;
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

        //Here is the code of how to navigate with buttons for the Main Activity button
        ImageView button_am = findViewById(R.id.btn_go_back_am);
        button_am.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start Activity, here we send the activity from where we are to where it will take us
                Intent intent = new Intent(am.this, MainActivity.class);
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

        ImageView ButtonPlayAm = findViewById(R.id.img_play_am);

        ButtonPlayAm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //START CODE
                mediaPlayer.start();
            }
        });

        //------------------------------------------------------------------------------------------------------


        ImageView ButtonPauseAm = findViewById(R.id.img_pause_am);

        ButtonPauseAm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //PAUSE CODE
                mediaPlayer.pause();
            }
        });

        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------END OF THIS SECTION-----------------------------------------------------
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
    //-----------------------------------------END OF THIS SECTION-------------------------------------------------------
    //-------------------------------------------------------------------------------------------------------------------
}


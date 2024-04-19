package com.example.radiotvonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class slash extends AppCompatActivity {

    private static final int TIEMPO_DEMOSTRACION_IMAGEN = 3000; //TIEMPO QUE DURA VISIBLE 1000 ES UN SEGUNDO LO CUAL AHI VA A DURAR 3 SEGUNDOS

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slash);

        final ImageView imageView = findViewById(R.id.slash); //WE ASSIGN OUR ID FROM OUR SLASH
        imageView.setImageResource(R.drawable.butonsmrtvodos); // WE ESTABLISH OUR IMAGE THAT APPEARS IN OUR SLASH

        //TELL THE APP WHAT YOU WILL DO AFTER IT FINISHES SHOWING OUR SLASH
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(slash.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // CLEAN THE ACTIVITY STACK IF THE MAINACTIVITY IS ALREADY IN IT
                startActivity(intent); //START OUR ACTION BY MOVING TO OUR MAINACTIVITY
                finish(); // END OUR SLASH
            }
        }, TIEMPO_DEMOSTRACION_IMAGEN);


    }
}

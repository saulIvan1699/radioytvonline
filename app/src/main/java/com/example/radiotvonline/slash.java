package com.example.radiotvonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class slash extends AppCompatActivity {

    private static final int TIEMPO_DEMOSTRACION_IMAGEN = 3000; // Tiempo en milisegundos (3 segundos)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slash);

        final ImageView imageView = findViewById(R.id.slash); // Suponiendo que tu ImageView tiene el ID "slash"
        imageView.setImageResource(R.drawable.butonsmrtvodos); // Establecer la imagen en el ImageView

        // Programar la transición a MainActivity después de un tiempo determinado (3 segundos)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(slash.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Limpiar el stack de actividades si MainActivity ya está en él
                startActivity(intent); // Iniciar MainActivity
                finish(); // Finalizar slash para que no se pueda volver atrás
            }
        }, TIEMPO_DEMOSTRACION_IMAGEN);

    }
}

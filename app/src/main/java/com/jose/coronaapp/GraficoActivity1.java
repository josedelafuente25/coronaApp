package com.jose.coronaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GraficoActivity1 extends AppCompatActivity {


   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico1);

    }

    public void retorno_graf (View view) {

        Intent pantalla_resumen = new Intent(this, Resumen_Activity.class);
        startActivity(pantalla_resumen);

    }

    }

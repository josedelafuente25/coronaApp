package com.jose.coronaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MapaActivity extends AppCompatActivity {
    Spinner comboRegiones;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        comboRegiones= (Spinner) findViewById(R.id.spinnerRegiones);

        final ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(
                this, R.array.combo_regiones, android.R.layout.simple_spinner_item);


        comboRegiones.setAdapter(adapter);

        comboRegiones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    public void retorno_map (View view) {

        Intent pantalla_resumen = new Intent(this, Resumen_Activity.class);
        startActivity(pantalla_resumen);

    }

    }

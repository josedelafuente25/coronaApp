package com.jose.coronaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.ArrayLinkedVariables;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class Resumen_Activity extends AppCompatActivity {
   private TextView fechaCompleta;
   private TextView cambiarNombre;
   Spinner comboRegiones;


    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_resumen);

        Date d = new Date();

        fechaCompleta = findViewById(R.id.txt_fecha);
        SimpleDateFormat fecc = new SimpleDateFormat("d MMMM 'del' yyyy");
        String fechacComplString = fecc.format(d);
        fechaCompleta.setText(fechacComplString);


        comboRegiones= (Spinner) findViewById(R.id.spinnerRegiones);
       // cambiarNombre= findViewById(R.id.nombreTextV);



        final ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(
                this, R.array.combo_regiones, android.R.layout.simple_spinner_item);


        comboRegiones.setAdapter(adapter);

        comboRegiones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //cambiarNombre.setText(("A"));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }


    public void cambiar_pantalla(View view) {
        int opcion = view.getId();

        switch(opcion) {

            case R.id.btn_atras:
                Intent pantalla_inicio = new Intent(this, MainActivity.class);
                startActivity(pantalla_inicio);
                Toast.makeText(this, "Pantalla Inicio", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_grafico:
                Intent activity_grafico1 = new Intent(this, GraficoActivity1.class);
                startActivity(activity_grafico1);
                Toast.makeText(this, "Pantalla graficos", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_mapa:
                Intent activity_maps1 = new Intent(this, MapsActivity.class);
                startActivity(activity_maps1);
                Toast.makeText(this, "Pantalla mapa", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

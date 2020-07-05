package com.jose.coronaapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class GraficoActivity1 extends AppCompatActivity {
Spinner comboRegiones;
PieChartView pieChartView;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico1);

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


       PieChartView pieChartView = findViewById(R.id.chart);

       pieChartView = findViewById(R.id.chart);

       List pieData = new ArrayList<>();
       pieData.add(new SliceValue(15, Color.BLUE).setLabel("Nuevos casos: 132"));
       pieData.add(new SliceValue(25, Color.RED).setLabel("Nuevos Fallecidos: 3"));
       pieData.add(new SliceValue(10, Color.YELLOW).setLabel("Casos totales: 2176"));
       pieData.add(new SliceValue(60, Color.GREEN).setLabel("Recuperados: 1876"));
       pieData.add(new SliceValue(60, Color.MAGENTA).setLabel("Fallecidos totales: 46"));

       PieChartData pieChartData = new PieChartData(pieData);
       pieChartData.setHasLabels(true).setValueLabelTextSize(14);
       pieChartData.setHasCenterCircle(true).setCenterText1("Region Araucania").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
       pieChartView.setPieChartData(pieChartData);

    }
    public void retorno_graf (View view) {

        Intent pantalla_resumen = new Intent(this, Resumen_Activity.class);
        startActivity(pantalla_resumen);

    }

    }

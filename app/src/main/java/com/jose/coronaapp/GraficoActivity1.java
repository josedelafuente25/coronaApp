package com.jose.coronaapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.jose.coronaapp.objetos.RegionApp;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class GraficoActivity1 extends AppCompatActivity {
Spinner comboRegiones;


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
               String Seleccionado=parent.getItemAtPosition(position).toString();
               for (RegionApp r:Resumen_Activity.listaRegiones2) {
                   if (Seleccionado.equals(r.getNOMBRECORTO())){
                       float sumaTotal;
                       sumaTotal= r.getT_CONF()+r.getT_REC()+r.getT_FALL()+r.getN_ACT();

                       float porcentajeConfirmados;
                       porcentajeConfirmados= (r.getT_CONF()*100)/sumaTotal;
                       float porcentajeActivos;
                       porcentajeActivos= (r.getN_ACT()*100)/sumaTotal;
                       float porcentajeFallecidos;
                       porcentajeFallecidos= (r.getT_FALL()*100)/sumaTotal;
                       float porcentajeRecuperados;
                       porcentajeRecuperados= (r.getT_REC()*100)/sumaTotal;

                       PieChartView pieChartView;
                       pieChartView = findViewById(R.id.chart2);

                       List pieData = new ArrayList<>();
                       pieData.add(new SliceValue(porcentajeActivos, Color.BLUE).setLabel("A: "+r.getN_ACT()));
                       pieData.add(new SliceValue(porcentajeFallecidos, Color.RED).setLabel("F: "+r.getT_FALL()));
                       pieData.add(new SliceValue(porcentajeConfirmados, Color.CYAN).setLabel("T: "+r.getT_CONF()));
                       pieData.add(new SliceValue(porcentajeRecuperados, Color.GREEN).setLabel("R: "+r.getT_REC()));

                       PieChartData pieChartData = new PieChartData(pieData);
                       pieChartData.setHasLabels(true).setValueLabelTextSize(14);
                       pieChartData.setHasCenterCircle(true).setCenterText1(" "+r.getNOMBRECORTO()).setCenterText1FontSize(14).setCenterText1Color(Color.parseColor("#FFFFFF"));
                       pieChartView.setPieChartData(pieChartData);
                   }
               }
           }
           @Override
           public void onNothingSelected(AdapterView<?> parent) {
           }
       });
    }
    public void retorno_graf (View view) {
        Intent pantalla_resumen = new Intent(this, Resumen_Activity.class);
        startActivity(pantalla_resumen);
    }
    public void cambiar_graf (View view) {
        Intent activity_grafico2 = new Intent(this, GraficoActivity2.class);
        startActivity(activity_grafico2);
    }

    }

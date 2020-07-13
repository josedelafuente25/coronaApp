package com.jose.coronaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jose.coronaapp.objetos.RegionApp;

import java.util.ArrayList;
import java.util.List;

public class GraficoActivity2 extends AppCompatActivity {
    Spinner comboRegiones;
    BarChart graficoBarras;
    float porcentajeConfirmados;
    float porcentajeActivos;
    float porcentajeFallecidos;
    float porcentajeRecuperados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico2);
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


                        porcentajeConfirmados= (r.getT_CONF()*100)/sumaTotal;
                        porcentajeActivos= (r.getN_ACT()*100)/sumaTotal;
                        porcentajeFallecidos= (r.getT_FALL()*100)/sumaTotal;
                        porcentajeRecuperados= (r.getT_REC()*100)/sumaTotal;


                        graficoBarras = findViewById(R.id.graficaBarras);
                        List<BarEntry> entradas = new ArrayList<>();
                        entradas.add(new BarEntry(0f, r.getT_CONF()));
                        entradas.add(new BarEntry(1f, r.getT_FALL()));
                        entradas.add(new BarEntry(2f, r.getT_REC()));
                        entradas.add(new BarEntry(3f, r.getN_ACT()));

                        BarDataSet datos = new BarDataSet(entradas, "Confirmados, Fallecidos, Recuperados, Activos");
                        BarData data = new BarData(datos);
                        datos.setColors(ColorTemplate.COLORFUL_COLORS);

                        data.setBarWidth(0.9F);
                        Description description = new Description();
                        description.setText("");
                        graficoBarras.setDescription(description);

                        graficoBarras.setData(data);
                        graficoBarras.setFitBars(true);
                        graficoBarras.invalidate();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    public void retorno_graf(View view) {
        Intent pantalla_resumen = new Intent(this, Resumen_Activity.class);
        startActivity(pantalla_resumen);
    }

    public void cambiar_graf(View view) {

        Intent activity_grafico1 = new Intent(this, GraficoActivity1.class);
        startActivity(activity_grafico1);

    }

}

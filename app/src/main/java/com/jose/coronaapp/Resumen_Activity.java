package com.jose.coronaapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Resumen_Activity extends AppCompatActivity {

   private TextView fechaCompleta;

   // private Button btn_atras;
   // private Button btn_grafico;
    //private Button btn_mapa;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_resumen);

        Date d = new Date();

        fechaCompleta = findViewById(R.id.txt_fecha);
        SimpleDateFormat fecc = new SimpleDateFormat("d MMMM 'del' yyyy");
        String fechacComplString = fecc.format(d);
        fechaCompleta.setText(fechacComplString);

       // btn_grafico =findViewById(R.id.btn_grafico);
      //  btn_mapa =findViewById(R.id.btn_mapa);
       // btn_atras =findViewById(R.id.btn_atras);
    }


   // public void volver(View view) {
       // Intent pantalla_inicio = new Intent(this, MainActivity.class);
       // startActivity(pantalla_inicio);
        //Toast.makeText(this, "Pantalla Inicio", Toast.LENGTH_SHORT).show();


   // }

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
                Intent activity_mapa = new Intent(this, MapaActivity.class);
                startActivity(activity_mapa);
                Toast.makeText(this, "Pantalla mapa", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

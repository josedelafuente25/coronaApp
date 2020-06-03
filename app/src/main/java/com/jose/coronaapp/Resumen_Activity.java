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
    TextView fechaCompleta;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_resumen);
        Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();

        Date d=new Date();

        fechaCompleta = (TextView) findViewById(R.id.txt_fecha);
        SimpleDateFormat fecc=new SimpleDateFormat("d, MMMM 'del'yyyy");
        String fechacComplString = fecc.format(d);
        fechaCompleta.setText(fechacComplString);

    }

    public void volver (View view) {
        Intent pantalla_inicio  = new Intent(this,MainActivity.class);
    startActivity(pantalla_inicio);
        Toast.makeText(this, "Pantalla Inicio", Toast.LENGTH_SHORT).show();


}

}


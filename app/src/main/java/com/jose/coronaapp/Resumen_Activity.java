package com.jose.coronaapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class Resumen_Activity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_resumen);
        Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();


    }

    public void volver (View view) {
        Intent pantalla_inicio  = new Intent(this,MainActivity.class);
    startActivity(pantalla_inicio);
        Toast.makeText(this, "Pantalla Inicio", Toast.LENGTH_SHORT).show();
}
}


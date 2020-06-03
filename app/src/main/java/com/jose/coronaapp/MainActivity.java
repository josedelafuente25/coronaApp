package com.jose.coronaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;




public class MainActivity extends AppCompatActivity{

private Button btn_ingresar;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.pantalla_inicio);
        btn_ingresar =findViewById(R.id.btnIngresar);


        }

public void ingresar(View view) {
    Intent pantalla_resumen = new Intent(this,Resumen_Activity.class);
    startActivity(pantalla_resumen);

}



    }




package com.jose.coronaapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jose.coronaapp.Adapter.MyAdapter;
import com.jose.coronaapp.objetos.Region;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Resumen_Activity extends AppCompatActivity {

    private TextView fechaCompleta;
    Spinner comboRegiones;
    ProgressDialog pd;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_resumen);

        recyclerView = findViewById(R.id.recyclerJSON);
        JsonTask asyncTask=new JsonTask();
        asyncTask.execute("https://my-json-server.typicode.com/josedelafuente25/coronajson/db");
        //capturar fecha del PC

        Date d = new Date();

        fechaCompleta = findViewById(R.id.txt_fecha);
        SimpleDateFormat fecc = new SimpleDateFormat("d MMMM 'del' yyyy");
        String fechacComplString = fecc.format(d);
        fechaCompleta.setText(fechacComplString);

        //arreglo Spinner

        comboRegiones = (Spinner) findViewById(R.id.spinnerRegiones);

        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
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


    public void cambiar_pantalla(View view) {
        int opcion = view.getId();

        switch (opcion) {

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

    private class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(Resumen_Activity.this);
            pd.setMessage("Espere por favor...");
            pd.setIndeterminate(false);
            pd.setCancelable(false);
            pd.show();
        }

        protected String doInBackground(String... params) {

            String miurl="https://my-json-server.typicode.com/josedelafuente25/coronajson/db";
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                //URL url = new URL(params[0]); // o
                URL url = new URL(miurl);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                    //Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)
                }

                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pd.isShowing()){
                pd.dismiss();
            }
            //txtJson.setText(result);
            //llamado a funciones
            trabajarJson(result);
        }
    }

    public void trabajarJson (String resultado)  {

        ArrayList<Region> listaRegiones = new ArrayList<>();
        //convirtiendo el resultado a JSON
        try {
            JSONObject jsonObject = new JSONObject(resultado);

            //String stringJSON = jsonObject.getString("Region");

            JSONArray jsonArray = jsonObject.getJSONArray("Region");
            JSONObject jsonObjectHijo;
            for (int i = 0; i < jsonArray.length(); i++)
            {
                try {
                    Region region = new Region();
                    //al hijo del json por posicion
                    jsonObjectHijo = jsonArray.getJSONObject(i);
                    //extraer el nombre y el id, asignando al objeto
                    region.setNombre(jsonObjectHijo.getString("Nombre"));
                    //asignar a string para posteriormente convertirlo a int
                    String id = jsonObjectHijo.getString("id");
                    region.setId(Integer.parseInt(id));
                    listaRegiones.add(region);

                } catch (JSONException e) {
                    Log.e("Parser JSON", e.toString());
                }
            }

            MyAdapter adaptadorRecycler = new MyAdapter(this, listaRegiones);
            recyclerView.setAdapter(adaptadorRecycler);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
            recyclerView.addItemDecoration(dividerItemDecoration);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
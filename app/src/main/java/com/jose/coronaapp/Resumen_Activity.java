package com.jose.coronaapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jose.coronaapp.Adapter.MyAdapter;
import com.jose.coronaapp.objetos.RegionApp;

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
    ProgressDialog pd;
    private RecyclerView recyclerView;
    public static ArrayList<RegionApp> listaRegiones2 = new ArrayList<>();


    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_resumen);

        recyclerView = findViewById(R.id.recyclerJSON);
        JsonTask asyncTask=new JsonTask();
        asyncTask.execute("https://services1.arcgis.com/LsoiDXzijohT7g97/arcgis/rest/services/COVID19ChileV2/FeatureServer/0/query?where=1%3D1&outFields=*&returnGeometry=false&outSR=4326&f=json");

        Date d = new Date();
        fechaCompleta = findViewById(R.id.txt_fecha);
        SimpleDateFormat fecc = new SimpleDateFormat("d MMMM 'del' yyyy");
        String fechacComplString = fecc.format(d);
        fechaCompleta.setText(fechacComplString);

    }
    public void cambiar_pantalla(View view) {
        int opcion = view.getId();

        switch (opcion) {

            case R.id.btn_atras:
                Intent pantalla_inicio = new Intent(this, MainActivity.class);
                startActivity(pantalla_inicio);
                break;

            case R.id.btn_grafico:
                Intent activity_grafico1 = new Intent(this, GraficoActivity1.class);
                startActivity(activity_grafico1);
                break;

            case R.id.btn_mapa:
                Intent activity_maps = new Intent(this, MapsActivity.class);
                startActivity(activity_maps);
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

            String miurl="https://services1.arcgis.com/LsoiDXzijohT7g97/arcgis/rest/services/COVID19ChileV2/FeatureServer/0/query?where=1%3D1&outFields=*&returnGeometry=false&outSR=4326&f=json";
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(miurl);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
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

            trabajarJson(result);
        }
    }

    public void trabajarJson (String resultado)  {
        ArrayList<RegionApp> listaRegiones = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(resultado);
            JSONArray jsonArray = jsonObject.getJSONArray("features");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject nombre= jsonArray.getJSONObject(i);
                JSONObject attributes=nombre.getJSONObject("attributes");

                listaRegiones.add(new RegionApp(attributes.getInt("IDREGION"),attributes.getString("NOMBRECORTO"),
                        attributes.getInt("T_CONF"),attributes.getInt("T_FALL"),attributes.getInt("T_REC"),attributes.getInt("N_ACT")));
            }

                this.listaRegiones2=listaRegiones;
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
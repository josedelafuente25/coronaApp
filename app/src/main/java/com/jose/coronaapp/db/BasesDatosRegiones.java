package com.jose.coronaapp.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Vector;

public class BasesDatosRegiones extends SQLiteOpenHelper {


    public BasesDatosRegiones(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creamos la tabla
        String sQuery= "CREATE TABLE regionesTable (id INTEGER PRIMARY KEY AUTOINCREMENT, regionNombre TEXT, nuevosCasos INTEGER, contagiadosTotales INTEGER, recuperadosTotales INTEGER, totalFallecidos INTEGER )";
        db.execSQL(sQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
//metodo inserción
    public void guardarRegion(String regionNombre, int nuevosCasos, int contagiadosTotales, int recuperadosTotales, int totalFallecidos){
        SQLiteDatabase db = getWritableDatabase();
        String sQuery = "INSERT INTO regionesTable VALUES (regionNombre, nuevosCasos, contagiadosTotales, recuperadosTotales, totalFallecidos)";
        db.execSQL(sQuery);
        db.close();
    }
//metodo de consulta
    public Vector<String> consultarRegiones() {
        //almacena el resultado de la Query
        Vector <String> resultado = new Vector<String>();

        //crear variable para ejecutar query
        SQLiteDatabase db = getReadableDatabase();
        String sQuery = "SELECT regionNombre, nuevosCasos, contagiadosTotales, recuperadosTotales, totalFallecidos FROM regionesTable ORDER BY region ";
        //almacenamos el resultado de la query
        Cursor cursor = db.rawQuery(sQuery, null);

        while(cursor.moveToNext()) {
            //agregar al vector
            resultado.add(cursor.getString(0)+ " "+cursor.getInt(1)+ " "+cursor.getInt(2)+ " "+cursor.getInt(3)+ " "+cursor.getInt(4));
        }
        //cerramos conexión y cursor
        cursor.close();
        db.close();
        //retornando los resultados
        return resultado;

    }
}

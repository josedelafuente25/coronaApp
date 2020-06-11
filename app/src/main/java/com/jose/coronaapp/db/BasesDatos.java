package com.jose.coronaapp.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Vector;

public class BasesDatos extends SQLiteOpenHelper {


    public BasesDatos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creamos la tabla
        String sQuery= "CREATE TABLE notas (id INTEGER PRIMARY KEY AUTOINCREMENT, nota FLOAT, detalle TEXT)";
        db.execSQL(sQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
//metodo inserción
    public void guardarNotas(Float nota, String detalle){
        SQLiteDatabase db = getWritableDatabase();
        String sQuery = "INSERT INTO notas VALUES ("+ nota +", "+detalle+")";
        db.execSQL(sQuery);
        db.close();
    }
//metodo de consulta
    public Vector<String> consultarNotas(int iTotal) {
        //almacena el resultado de la Query
        Vector <String> resultado = new Vector<String>();

        //crear variable para ejecutar query
        SQLiteDatabase db = getReadableDatabase();
        String sQuery = "SELECT nota, detalle FROM notas ORDER BY nota desc LIMIT" +iTotal;
        //almacenamos el resultado de la query
        Cursor cursor = db.rawQuery(sQuery, null);

        while(cursor.moveToNext()) {
            //agregar al vector
            resultado.add(cursor.getFloat(0)+ " "+cursor.getString(1) );
        }
        //cerramos conexión y cursor
        cursor.close();
        db.close();
        //retornando los resultados
        return resultado;

    }
}

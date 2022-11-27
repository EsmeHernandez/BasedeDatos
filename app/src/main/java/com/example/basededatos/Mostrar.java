package com.example.basededatos;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Mostrar extends AppCompatActivity {

    public ListView datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        datos=(ListView)findViewById(R.id.lista1);
    }
     public void  enlistar(View view){
        double promedio=0;
         AdminSQLitedOpendHelper admin=new AdminSQLitedOpendHelper(this, "administacion", null, 1);
         SQLiteDatabase db=admin.getWritableDatabase();
         ArrayList<String> valores=new ArrayList<>();
         Cursor fila=db.rawQuery("select * from calificaciones", null);
         if (fila.moveToFirst()){
             do{
                 valores.add("id" + fila.getString(0)+"\t"+ "Nombre"+fila.getString(1)+"\t"
                         + "calificaion1: " + fila.getString(2)+"\t"+ "Calificaion 2:" + fila.getString(3)
                 +"\t" + "Calificaion 3:"+ fila.getString(3));
             } while (fila.moveToFirst());
                 }
         ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, valores);
         datos.setAdapter(adaptador);
             }
         }




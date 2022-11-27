package com.example.basededatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLitedOpendHelper  extends SQLiteOpenHelper {
    public AdminSQLitedOpendHelper(Context context, String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table alumnos (numero integer primary key, nombre text, " +
                "carrera text, semestre integer)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists" + " alumnos");
        sqLiteDatabase.execSQL("create table alumnos(numero integer primary key, " +
                "nombre text, carrera text, semestre integer)");


    }
}

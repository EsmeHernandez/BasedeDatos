package com.example.basededatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public EditText ed1,ed2,ed3,ed4;
    public Button button, button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1 = (EditText) findViewById(R.id.ed1);
        ed2 = (EditText) findViewById(R.id.ed2);
        ed3 = (EditText) findViewById(R.id.ed3);
        ed4 = (EditText) findViewById(R.id.ed4);
    }

    public void altas(View view) {

        AdminSQLitedOpendHelper admin = new AdminSQLitedOpendHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nombre1 = ed1.getText().toString();
        String carrera1 = ed2.getText().toString();
        String semestre1 = ed3.getText().toString();
        String numero1 = ed4.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("numero", numero1);
        registro.put("nombre", nombre1);
        registro.put("carrera", carrera1);
        registro.put("semestre", semestre1);
        bd.insert("alumnos", null, registro);//Inserto la base de datos alumnos
        bd.close();
        //Mensaje emergente
        Toast.makeText(this, "Datos del usuario cargados con existo", Toast.LENGTH_SHORT).show();
        this.limpiar();
    }

    public void limpiar() {
        ed1.setText("");
        ed2.setText("");
        ed3.setText("");
        ed4.setText("");
    }

    public void busqueda(View view) {
        AdminSQLitedOpendHelper admin = new AdminSQLitedOpendHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String numero1 = ed4.getText().toString();
        Cursor fila = bd.rawQuery("select nombre, carrera, semestre from alumnos where numero=" + numero1, null);
        //Toast.makeText(this, "voy aqui", Toast.LENGTH_SHORT).show();
        if (fila.moveToFirst()) {
            ed1.setText(fila.getString(0));
            ed2.setText(fila.getString(1));
            ed3.setText(fila.getString(2));
        } else
            Toast.makeText(this, "No existe un alumno con ese numero de control", Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void bajas(View view) {
        AdminSQLitedOpendHelper admin = new AdminSQLitedOpendHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String numero1 = ed4.getText().toString();
        int cantidad = bd.delete("alumnos", "numero=" + numero1, null);
        this.limpiar();
        if (cantidad == 1)
            Toast.makeText(this, "Numero de control eliminado", Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "voy aqui", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "El registro no se encontro", Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void modificaciones(View view) {
        AdminSQLitedOpendHelper admin = new AdminSQLitedOpendHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nombre1 = ed1.getText().toString();
        //Toast.makeText(this, "voy aqui", Toast.LENGTH_SHORT).show();
        String carrera1 = ed2.getText().toString();
        String semestre1 = ed3.getText().toString();
        String numero1 = ed4.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("numero", numero1);
        registro.put("nombre", nombre1);
        registro.put("carrera", carrera1);
        registro.put("semestre", semestre1);
        int cantidad=bd.update("alumnos", registro, "numero="+numero1, null);
        bd.close();
        if (cantidad == 1)
            Toast.makeText(this, "Datos modifiados con exito", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Datos no modificados", Toast.LENGTH_SHORT).show();

    }
}
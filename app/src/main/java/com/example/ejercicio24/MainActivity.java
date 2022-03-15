package com.example.ejercicio24;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ejercicio24.configuraciones.SQLiteConexion;
import com.example.ejercicio24.configuraciones.Transacciones;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity  {

    Lienzo lienzo;
    ImageButton nuevo;
    Button salvar;
    EditText descripcion;
    SQLiteConexion conexion;
    Bitmap ima;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase,null,1);

        nuevo = (ImageButton) findViewById(R.id.btnN);

        salvar = (Button) findViewById(R.id.btnSalvar);

        descripcion = (EditText) findViewById(R.id.textDescripcion);


        lienzo = (Lienzo) findViewById(R.id.lienzo);


        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("¿Seguro de crear nuevo dibujo, perderá los datos actuales?")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                lienzo.nuevoDibujo();
                                descripcion.setText("");
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).show();

            }
        });

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSignaturess();
            }
        });

    }

    private void saveSignaturess(){
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Transacciones.descripcion,descripcion.toString());

        lienzo.setDrawingCacheEnabled(true);

        ByteArrayOutputStream bay = new ByteArrayOutputStream(10480);

        Bitmap bitmap = Bitmap.createBitmap(339, 334, Bitmap.Config.ARGB_8888);

        bitmap.compress(Bitmap.CompressFormat.JPEG, 0 , bay);

        byte[] bl = bay.toByteArray();

        values.put(Transacciones.imagen, bl);

        Long result = db.insert(Transacciones.tablasignaturess, Transacciones.id, values);

        Toast.makeText(getApplicationContext(), "Signature guardado "
                ,Toast.LENGTH_LONG).show();

        db.close();
    }


}
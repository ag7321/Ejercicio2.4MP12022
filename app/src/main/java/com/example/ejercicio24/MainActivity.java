package com.example.ejercicio24;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Base64;
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
    ImageButton nuevo,ver;
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
        ver = (ImageButton)findViewById(R.id.btnVer);

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

        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivitySeeSignaturess.class);
                startActivity(intent);
            }
        });

    }

    private void saveSignaturess(){
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Transacciones.descripcion,descripcion.getText().toString());
        ByteArrayOutputStream bay = new ByteArrayOutputStream(10480);

        Bitmap bitmap = Bitmap.createBitmap(lienzo.getWidth(), lienzo.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        lienzo.draw(canvas);

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100 , bay);

        byte[] bl = bay.toByteArray();

        String img= Base64.encodeToString(bl,Base64.DEFAULT);

        values.put(Transacciones.imagen, img);

        Long result = db.insert(Transacciones.tablasignaturess, Transacciones.id, values);

        Toast.makeText(getApplicationContext(), "Signature guardado"
                ,Toast.LENGTH_LONG).show();

        db.close();
    }


}
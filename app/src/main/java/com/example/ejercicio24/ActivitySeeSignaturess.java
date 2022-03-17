package com.example.ejercicio24;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.ejercicio24.configuraciones.SQLiteConexion;
import com.example.ejercicio24.configuraciones.Transacciones;

import java.util.ArrayList;
import java.util.List;

public class ActivitySeeSignaturess extends AppCompatActivity {
    SQLiteConexion conexion;

    RecyclerView.Adapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Signaturess> signaturessList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_signaturess);

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase,null,1);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        signaturessList = new ArrayList<>();


        getSignaturess();


        adapter = new Adapter(signaturessList);
        recyclerView.setAdapter(adapter);
    }


    private void getSignaturess(){
        SQLiteDatabase db = conexion.getReadableDatabase();
        Signaturess signaturess = null;
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Transacciones.tablasignaturess,null);
        while (cursor.moveToNext()){
            signaturess = new Signaturess();
            signaturess.setId(cursor.getInt(0));
            signaturess.setDescripcion(cursor.getString(1));
            signaturess.setImagen(cursor.getString(2));

            signaturessList.add(signaturess);

        }
    }



}
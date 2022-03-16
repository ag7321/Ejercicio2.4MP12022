package com.example.ejercicio24;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.ejercicio24.configuraciones.SQLiteConexion;
import com.example.ejercicio24.configuraciones.Transacciones;

import java.util.ArrayList;
import java.util.List;

public class ActivitySeeSignaturess extends AppCompatActivity {
    SQLiteConexion conexion;
    RecyclerView idrecyclerView;
    ArrayList<Signaturess> signaturessList;
    Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_signaturess);

        //idrecyclerView = (RecyclerView)findViewById(R.id.recyclerView);




        conexion = new SQLiteConexion(this, Transacciones.NameDatabase,null,1);



        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        //lManager = new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(new GridLayoutManager());
        recyclerView.setAdapter(adapter);
        getSignaturess();
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
            adapter.agregarSignaturess(signaturess);
        }


    }



}
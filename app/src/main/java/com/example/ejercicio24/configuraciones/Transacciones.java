package com.example.ejercicio24.configuraciones;

public class Transacciones {
    //Nombre de la base de datos
    public static final String NameDatabase = "signaturess";

    //Creacion de las tablas de la base de datos

    public static final String tablasignaturess = "signaturess";

    /*
    *
    * Campos especificos de la tabla Personas
    * */

    public static final String id = "id";
    public static final String descripcion = "descripcion";
    public static final String imagen = "imagen";


    public static final String CreateTableSignaturess = "CREATE TABLE "+tablasignaturess + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "descripcion TEXT, imagen BLOB)";

    public static final String DropTableSignaturess = "DROP TABLE IF EXISTS "+ tablasignaturess;
}

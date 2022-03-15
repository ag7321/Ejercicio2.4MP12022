package com.example.ejercicio24;

public class signaturess {
    Integer id;
    String Descripcion;
    byte[] imagen;

    public signaturess() {

    }

    public signaturess(Integer id, String descripcion, byte[] imagen) {
        this.id = id;
        Descripcion = descripcion;
        this.imagen = imagen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}

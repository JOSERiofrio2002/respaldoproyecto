package com.example.models;

public class Equipo {
    private Integer id;
    private Integer idInscripcion;
    private String nombre;


    public Equipo() {
    }

    public Equipo(Integer id, Integer idInscripcion, String nombre) {
        this.id = id;
        this.idInscripcion = idInscripcion;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(Integer idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}

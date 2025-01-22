package com.example.models;

public class Fase {
    private Integer id;
    private String nombreFase;
    
    public Fase() {
    }

    public Fase(Integer id, String nombreFase) {
        this.id = id;
        this.nombreFase = nombreFase;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreFase() {
        return nombreFase;
    }

    public void setNombreFase(String nombreFase) {
        this.nombreFase = nombreFase;
    }

}

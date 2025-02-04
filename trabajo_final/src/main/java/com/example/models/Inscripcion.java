package com.example.models;


public class Inscripcion {
    private Integer id;
    private Double valorInscripcion;

    public Inscripcion() {
    }

    public Inscripcion(Integer id, Double valorInscripcion) {
        this.id = id;
        this.valorInscripcion = valorInscripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValorInscripcion() {
        return valorInscripcion;
    }

    public void setValorInscripcion(Double valorInscripcion) {
        this.valorInscripcion = valorInscripcion;
    }

}


package com.example.models;

public class Inscripcion {
    private Integer id;
    private Float valorInscripcion;

    public Inscripcion() {
    }

    public Inscripcion(Integer id, Float valorInscripcion) {
        this.id = id;
        this.valorInscripcion = valorInscripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getValorInscripcion() {
        return valorInscripcion;
    }

    public void setValorInscripcion(Float valorInscripcion) {
        this.valorInscripcion = valorInscripcion;
    }

}

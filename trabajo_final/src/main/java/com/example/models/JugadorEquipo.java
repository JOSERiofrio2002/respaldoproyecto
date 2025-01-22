package com.example.models;
import java.util.Date;

public class JugadorEquipo {
    private Integer id;
    private Integer IntegerJugador;
    private Integer idEquipo;
    private Date fechaInscrito;
    private String posicion;

    public JugadorEquipo() {
    }

    public JugadorEquipo(Integer id, Integer IntegerJugador, Integer idEquipo, Date fechaInscrito, String posicion) {
        this.id = id;
        this.IntegerJugador = IntegerJugador;
        this.idEquipo = idEquipo;
        this.fechaInscrito = fechaInscrito;
        this.posicion = posicion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIntegerJugador() {
        return IntegerJugador;
    }

    public void setIntegerJugador(Integer integerJugador) {
        IntegerJugador = integerJugador;
    }

    public Integer getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Date getFechaInscrito() {
        return fechaInscrito;
    }

    public void setFechaInscrito(Date fechaInscrito) {
        this.fechaInscrito = fechaInscrito;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

}

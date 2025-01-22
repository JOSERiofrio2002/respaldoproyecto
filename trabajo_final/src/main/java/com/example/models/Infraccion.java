package com.example.models;

import com.example.models.enumerador.ColorTarjeta;

public class Infraccion {
    private Integer id;
    private Integer numTarjeta;
    private String identificacionJugador;
    private ColorTarjeta colorTarjeta;

    public Infraccion() {
    }

    public Infraccion(Integer id, Integer numTarjeta, String identificacionJugador, ColorTarjeta colorTarjeta) {
        this.id = id;
        this.numTarjeta = numTarjeta;
        this.identificacionJugador = identificacionJugador;
        this.colorTarjeta = colorTarjeta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(Integer numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public String getIdentificacionJugador() {
        return identificacionJugador;
    }

    public void setIdentificacionJugador(String identificacionJugador) {
        this.identificacionJugador = identificacionJugador;
    }

    public ColorTarjeta getColorTarjeta() {
        return colorTarjeta;
    }

    public void setColorTarjeta(ColorTarjeta colorTarjeta) {
        this.colorTarjeta = colorTarjeta;
    }

    public void setTipo(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTipo'");
    }
}

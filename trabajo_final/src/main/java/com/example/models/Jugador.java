package com.example.models;

import java.util.Date;

import com.example.models.enumerador.Genero;
import com.example.models.enumerador.TipoIdentificacion;

public class Jugador extends Persona {
    private Integer numCamiseta;

    public Jugador() {
    }

    public Jugador(Integer id, boolean activo, String nombre, String apellido, TipoIdentificacion tipo, String identificacion,
            Date fechaNacimiento, String celular, Genero genero) {
        super(id, activo, nombre, apellido, tipo, identificacion, fechaNacimiento, celular, genero);
    }

    public Integer getNumCamiseta() {
        return numCamiseta;
    }

    public void setNumCamiseta(Integer numCamiseta) {
        this.numCamiseta = numCamiseta;
    }

}

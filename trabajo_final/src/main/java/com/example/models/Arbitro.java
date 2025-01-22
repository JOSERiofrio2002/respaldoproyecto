package com.example.models;

import java.util.Date;

import com.example.models.enumerador.Genero;
import com.example.models.enumerador.TipoIdentificacion;

public class Arbitro extends Persona {
    private String asociacion;

    public Arbitro(Integer id, boolean activo, String nombre, String apellido, TipoIdentificacion tipo, String identificacion,
            Date fechaNacimiento, String celular, Genero genero, String asociacion) {
        super(id, activo, nombre, apellido, tipo, identificacion, fechaNacimiento, celular, genero);
        this.asociacion = asociacion;
    }

    public Arbitro() {
    }

    public String getAsociacion() {
        return asociacion;
    }

    public void setAsociacion(String asociacion) {
        this.asociacion = asociacion;
    }
    
}

package com.example.models;
import java.util.Date;

import com.example.models.enumerador.TipoIdentificacion;
import com.example.models.enumerador.Genero;
public class Persona {
    private int id;
    private String nombre;
    private String apellido;
    private TipoIdentificacion tipo;
    private String identificacion;
    private Date fechaNacimiento;
    private String celular;
    private Genero genero;
    private boolean activo;

    public Persona() {
    }


    public Persona(int id,boolean activo, String nombre, String apellido, TipoIdentificacion tipo, String identificacion, Date fechaNacimiento, String celular, Genero genero) {
        this.id = id;
        this.activo = activo;//
        this.nombre = nombre;//
        this.apellido = apellido;//
        this.tipo = tipo;
        this.identificacion = identificacion;
        this.fechaNacimiento = fechaNacimiento;//
        this.celular = celular;//
        this.genero = genero;//
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public TipoIdentificacion getTipo() {
        return this.tipo;
    }

    public void setTipo(TipoIdentificacion tipo) {
        this.tipo = tipo;
    }

    public String getIdentificacion() {
        return this.identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCelular() {
        return this.celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Genero getGenero() {
        return this.genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }    
    public boolean getisActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}

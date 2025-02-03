package com.example.models;
import java.util.Date;

import com.example.models.enumerador.TipoCategoria;

public class Campeonato {
    private Integer id;
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private TipoCategoria categoria;

    public Campeonato() {
    }

    public Campeonato(Integer id, String nombre, Date fechaInicio, Date fechaFin, TipoCategoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.categoria = categoria;
    }

    public Campeonato(TipoCategoria categoria) {
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public TipoCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(TipoCategoria categoria) {
        this.categoria = categoria;
    }
}

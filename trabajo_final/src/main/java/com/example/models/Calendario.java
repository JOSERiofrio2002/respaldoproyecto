package com.example.models;
import java.util.Date;

public class Calendario {
    private Integer id;
    private Date fechaJornada;
    private Integer nroEncuentros;

    public Calendario() {
    }

    public Calendario(Integer id, Date fechaJornada, Integer nroEncuentros) {
        this.id = id;
        this.fechaJornada = fechaJornada;
        this.nroEncuentros = nroEncuentros;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaJornada() {
        return fechaJornada;
    }

    public void setFechaJornada(Date fechaJornada) {
        this.fechaJornada = fechaJornada;
    }

    public Integer getNroEncuentros() {
        return nroEncuentros;
    }

    public void setNroEncuentros(Integer nroEncuentros) {
        this.nroEncuentros = nroEncuentros;
    }

}

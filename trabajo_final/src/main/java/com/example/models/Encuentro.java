package com.example.models;
import java.sql.Time;

public class Encuentro {
    private Integer id;
    private Integer idInscrito1;
    private Integer idInscrito2;
    private String ubicacion;
    private String identificacion;
    private Boolean estado;
    private Time horaInicio;
    private Integer resultado;

    
    public Integer getResultado() {
        return resultado;
    }

    public void setResultado(Integer resultado) {
        this.resultado = resultado;
    }

    public Encuentro() {
    }

    public Encuentro(Integer id, Integer idInscrito1, Integer idInscrito2, String ubicacion, String identificacion, Boolean estado, Time horaInicio) {
        this.id = id;
        this.idInscrito1 = idInscrito1;
        this.idInscrito2 = idInscrito2;
        this.ubicacion = ubicacion;
        this.identificacion = identificacion;
        this.estado = estado;
        this.horaInicio = horaInicio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdInscrito1() {
        return idInscrito1;
    }

    public void setIdInscrito1(Integer idInscrito1) {
        this.idInscrito1 = idInscrito1;
    }

    public Integer getIdInscrito2() {
        return idInscrito2;
    }

    public void setIdInscrito2(Integer idInscrito2) {
        this.idInscrito2 = idInscrito2;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    
}

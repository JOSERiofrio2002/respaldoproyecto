package com.example.models.enumerador;

public enum Genero {
    MASCULINO("MASCULINO"), FEMENINO("FEMENINO");
    private String name;

    private Genero(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
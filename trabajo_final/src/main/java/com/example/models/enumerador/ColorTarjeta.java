package com.example.models.enumerador;

public enum ColorTarjeta {
    AMARILLO("AMARILLO"), ROJO("ROJO");
    private String name;

    private ColorTarjeta(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
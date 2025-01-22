package com.example.models.enumerador;

public enum TipoCategoria {
    MASCULINO("MASCULINO"), FEMENINA("FEMENINA");
    private String name;

    private TipoCategoria(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
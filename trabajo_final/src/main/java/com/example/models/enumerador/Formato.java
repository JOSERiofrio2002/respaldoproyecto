package com.example.models.enumerador;

public enum Formato {
    ELIMINACION("ELIMINACION"), TODOS_CONTRA_TODOS("TODOS_CONTRA_TODOS"), GRUPOS("GRUPOS");

    private String name;


    private Formato(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
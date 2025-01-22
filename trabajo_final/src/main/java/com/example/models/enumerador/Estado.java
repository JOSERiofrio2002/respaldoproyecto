package com.example.models.enumerador;

public enum Estado {
    INICIADO("INICIADO"), FINALIZADO("FINALIZADO"), SUSPENDIDO("SUSPENDIDO"), ENTRETIEMPO("ENTRETIEMPO");
    
    private String name;


    private Estado(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
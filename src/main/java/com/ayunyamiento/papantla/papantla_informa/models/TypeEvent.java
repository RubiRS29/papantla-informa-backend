package com.ayunyamiento.papantla.papantla_informa.models;

public enum TypeEvent {
    NEWS("Noticia"),
    EVENT("Evento");

    TypeEvent(String spanishType) {
        this.spanishType = spanishType;
    }

    private String spanishType;
}

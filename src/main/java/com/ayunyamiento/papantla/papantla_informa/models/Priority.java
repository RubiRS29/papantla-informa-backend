package com.ayunyamiento.papantla.papantla_informa.models;

public enum Priority {
        CRITICAL("CRÍTICA"),
        HIGH("ALTA"),
        MEDIUM("MEDIA"),
        LOW("BAJA");

    Priority(String spanishPriority) {
        this.spanishPriority = spanishPriority;
    }

    private String spanishPriority;
}

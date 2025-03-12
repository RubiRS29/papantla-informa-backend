package com.ayunyamiento.papantla.papantla_informa.models;

public enum Priority {
        CRITICAL("CRÍTICA", 5),
        HIGH("ALTA", 4),
        MEDIUM("MEDIA", 3),
        LOW("BAJA", 2),
        NONE("NINGUNA", 0);

    Priority(String spanishPriority, int priorityLevel) {
        this.spanishPriority = spanishPriority;
        this.priorityLevel = priorityLevel;
    }

    private String spanishPriority;
    private int priorityLevel;
}

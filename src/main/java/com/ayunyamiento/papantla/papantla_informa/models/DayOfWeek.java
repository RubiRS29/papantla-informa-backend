package com.ayunyamiento.papantla.papantla_informa.models;

public enum DayOfWeek {
    MONDAY("LUNES"), TUESDAY("MARTES"), WEDNESDAY("MIERCOLES"),
    THURSDAY("JUEVES"), FRIDAY("VIERNES"), SATURDAY("SABADO"),
    SUNDAY("DOMINGO"), ANY("GENERAL");


    DayOfWeek(String dayName) {
        this.dayName = dayName;
    }
    public String dayName;
}

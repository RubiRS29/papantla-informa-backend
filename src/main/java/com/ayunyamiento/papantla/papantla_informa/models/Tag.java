package com.ayunyamiento.papantla.papantla_informa.models;

public enum Tag {
    EVENT ("Evento"),
    OFFICIAL_ANNOUNCEMENT ("Anuncio Oficial"),
    SECURITY_ALERT ("Alerta de Seguridad"),
    CULTURAL_EVENT ("Evento Cultural"),
    TRAFFIC_UPDATE ( "Información de Tránsito"),
    SOCIAL_PROGRAMS("Programas Sociales"),
    PUBLIC_HEALTH ("Salud Pública"),
    SPORTS_ACTIVITIES_AND_WORKSHOP ("Deportes, Actividades y Talleres"),
    EMERGENCY ("Avisos de Emergencias"),
    PUBLIC_SERVICES ("Servicios Públicos");

    Tag(String tagTranslate) {
    }

    private String translateTag;
}

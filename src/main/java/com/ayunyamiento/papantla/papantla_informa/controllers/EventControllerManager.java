package com.ayunyamiento.papantla.papantla_informa.controllers;

import com.ayunyamiento.papantla.papantla_informa.models.dtos.EventsDTO;
import com.ayunyamiento.papantla.papantla_informa.services.EventsServicesManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/eventos")
public class EventControllerManager {

  private final EventsServicesManager eventsServicesManager;

    public EventControllerManager(EventsServicesManager eventsServicesManager) {
        this.eventsServicesManager = eventsServicesManager;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createEvent(@RequestPart("image") MultipartFile eventImage,
                                         @RequestParam ("eventsDTO") String  eventsDTOJson) throws IOException {

        // Convertir el JSON String a un objeto EventsDTO
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        EventsDTO eventsDTO = objectMapper.readValue(eventsDTOJson, EventsDTO.class);

        return ResponseEntity.ok(eventsServicesManager.createEvent(eventImage, eventsDTO));
    }

    @GetMapping()
    public ResponseEntity<?> getEvents() throws IOException {
        return ResponseEntity.ok(eventsServicesManager.getEventsOrderByPriority());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable Long id) throws IOException {
        return ResponseEntity.ok(eventsServicesManager.getEventById(id));
    }

    @PutMapping
    public ResponseEntity<?> updateEvent(@RequestBody EventsDTO eventsDTO) throws IOException {
        return ResponseEntity.ok(eventsServicesManager.updateEvent(eventsDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEventById(@PathVariable Long id) throws IOException {
        return ResponseEntity.ok(eventsServicesManager.getEventById(id));
    }
}

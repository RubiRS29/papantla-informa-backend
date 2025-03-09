package com.ayunyamiento.papantla.papantla_informa.services;

import com.ayunyamiento.papantla.papantla_informa.models.EventsModel;
import com.ayunyamiento.papantla.papantla_informa.models.dtos.EventsDTO;
import com.ayunyamiento.papantla.papantla_informa.repositories.EventsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.logging.Logger;

import static com.ayunyamiento.papantla.papantla_informa.models.mappers.EventMapper.EVENT_MAPPER;

@Service
public class EventsServicesManager {

    private final EventsRepository eventsRepository;
    private final String UPLOAD_FOLDER;
    private final String PATH_SEPARATOR;
    private final Logger LOGGER = Logger.getLogger(EventsServicesManager.class.getName());

    public EventsServicesManager(EventsRepository eventsRepository,
                                 @Value("${file.upload-dir}") String UPLOADED_FOLDER,
                                 @Value("${file.path.separator}") String PATH_SEPARATOR) {
        this.eventsRepository = eventsRepository;
        this.UPLOAD_FOLDER = UPLOADED_FOLDER;
        this.PATH_SEPARATOR = PATH_SEPARATOR;
    }


    public EventsDTO createEvent(MultipartFile file, EventsDTO eventsDTO) throws IOException {
        LOGGER.info("Creating new event");
        EventsModel eventsModel = EVENT_MAPPER.eventDTOToEventsModel(eventsDTO);
        String imagePath = saveImage(file);
        eventsModel.setEventImg(imagePath);

        EventsDTO event = EVENT_MAPPER.eventDTOToEventModel(eventsRepository.save(eventsModel));
        LOGGER.info("Created new event with priority: " + event.getPriority());

        return event;
    }

    public List<EventsDTO> getEventsOrderByPriority() throws IOException {
        LOGGER.info("Getting events by priority");

        List<EventsModel> all = eventsRepository.findAll();

        return event;
    }

    private String saveImage(MultipartFile file) throws IOException {
        LOGGER.info("Saving images into folder:" + UPLOAD_FOLDER);
        Path uploadPath  = Paths.get(UPLOAD_FOLDER + PATH_SEPARATOR);
        String fileName = file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return filePath.toString();
    }


}

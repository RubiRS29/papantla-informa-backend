package com.ayunyamiento.papantla.papantla_informa.models.mappers;

import com.ayunyamiento.papantla.papantla_informa.models.EventsModel;
import com.ayunyamiento.papantla.papantla_informa.models.dtos.EventsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventMapper EVENT_MAPPER = Mappers.getMapper(EventMapper.class);

    EventsDTO eventDTOToEventModel(EventsModel event);

    EventsModel eventDTOToEventsModel(EventsDTO event);

}

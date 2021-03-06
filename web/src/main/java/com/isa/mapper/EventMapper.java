package com.isa.mapper;


import com.isa.domain.api.EventApi;
import com.isa.domain.dto.EventDto;
import com.isa.domain.entity.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Stateless
public class EventMapper {

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());
    private final int START_YEAR_DATE = 0;
    private final int END_SECONDS_DATE = 19;


    public Event mapApiToEntity(EventApi eventApiParser) {
        logger.debug("Event mapping to Entity");

        Event event = new Event();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        event.setApiId(eventApiParser.getApiId());

        event.setStartDate(LocalDateTime.parse(eventApiParser.getStartDate().substring(START_YEAR_DATE,END_SECONDS_DATE),formatter));
        event.setEndDate(LocalDateTime.parse(eventApiParser.getEndDate().substring(START_YEAR_DATE,END_SECONDS_DATE),formatter));
        event.setDescShort(eventApiParser.getShortDescription());
        event.setName(eventApiParser.getName());
        event.setDescLong(eventApiParser.getLongDescription());
        event.setCategoryId(eventApiParser.getCategoryEventId());
        event.setActive(eventApiParser.getActiveEvent());

        logger.debug("Event mapping to DTO -> all parameters are set for Event ApiID {}", event.getApiId());

        return event;
    }

    public EventDto mapEntityToDto(Event eventEntity) {
        logger.debug("Event mapping to DTO");

        EventDto eventDto = new EventDto();

        eventDto.setId(eventEntity.getId());
        eventDto.setApiExternalId(eventEntity.getApiId());
        eventDto.setStartDate(eventEntity.getStartDate());
        eventDto.setEndDate(eventEntity.getEndDate());
        eventDto.setDescShort(eventEntity.getDescShort());
        eventDto.setName(eventEntity.getName());
        eventDto.setDescLong(eventEntity.getDescLong());
        eventDto.setCategoryId(eventEntity.getCategoryId());
        eventDto.setActive(eventEntity.isActive());

        logger.debug("Event mapping to DTO -> all parameters are set for Event ID {}", eventDto.getId());

        return eventDto;
    }

}

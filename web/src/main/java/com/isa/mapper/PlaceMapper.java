package com.isa.mapper;

import com.isa.domain.api.PlaceApi;
import com.isa.domain.dto.PlaceDto;
import com.isa.domain.entity.Place;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PlaceMapper {
    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Inject
    AddressMapper addressMapper;

    public Place mapApiToEntity(PlaceApi placeApi) {
        logger.debug("Place mapping to Entity");

        Place place = new Place();

        place.setApiId(placeApi.getApiId());
        place.setName(placeApi.getName());
        place.setSubName(placeApi.getDescription());

        logger.debug("Place mapping to Entity-> all parameters set  Place APiId {}", place.getApiId());


        return place;
    }

    public PlaceDto mapEntityToDto(Place place) {
        logger.debug("Place mapping to DTO");

        PlaceDto placeDto = new PlaceDto();

        placeDto.setId(place.getId());
        placeDto.setName(place.getName());
        placeDto.setSubname(place.getSubName());
        placeDto.setAddressDto(addressMapper.mapEntityToDto(place.getAddress()));

        logger.debug("Place mapping to DTO-> all parameters set  Place Id {}", placeDto.getId());
        ;

        return placeDto;
    }
}

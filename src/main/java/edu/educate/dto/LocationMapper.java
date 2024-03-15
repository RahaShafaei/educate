package edu.educate.dto;

import edu.educate.dto.baseDto.DtoMapperUtils;
import edu.educate.model.LocationEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class LocationMapper {
    private final DtoMapperUtils dtoMapperUtils;
    public LocationDto toDto(LocationEntity location) {

        LocationDto locationDto = new LocationDto();

        dtoMapperUtils.populateCommonFields(location, locationDto);

        locationDto.setTitle(location.getTitle());
        locationDto.setCode(location.getCode());
        locationDto.setDescr(location.getDescr());

        return locationDto;
    }
}

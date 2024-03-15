package edu.educate.service;

import edu.educate.dto.LocationDto;
import edu.educate.model.LocationEntity;
import edu.educate.service.baseService.GenericService;

import java.util.List;

public interface LocationService extends GenericService<LocationEntity, LocationDto> {
    List<LocationEntity> findByOrgUnitsId(Integer id);
}

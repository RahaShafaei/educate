package edu.educate.service;

import edu.educate.dto.LocationDto;
import edu.educate.model.LocationEntity;
import edu.educate.repository.LocationRepository;
import edu.educate.repository.OrgUnitRepository;
import edu.educate.service.baseService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("locationService")
public class LocationServiceImp extends GenericServiceImpl<LocationEntity, LocationDto> implements LocationService {
    @Autowired
    public LocationServiceImp(LocationRepository repository) {
        super(repository, "LocationEntity");
    }

    @Override
    public List<LocationEntity> findByOrgUnitsId(Integer id) {
        return ((LocationRepository) repository).findByOrgUnitsId(id);
    }
}

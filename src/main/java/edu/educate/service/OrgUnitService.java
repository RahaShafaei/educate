package edu.educate.service;

import edu.educate.dto.OrgUnitDto;
import edu.educate.dto.PersonDto;
import edu.educate.model.OrgUnitEntity;

import java.util.List;

public interface OrgUnitService {
    public List<OrgUnitDto> getOrgUnits();

    public OrgUnitDto getOrgUnit(Integer id);

    public Boolean deleteOrgUnit(Integer id);

    public OrgUnitDto createOrgUnit(OrgUnitEntity orgUnit);
}

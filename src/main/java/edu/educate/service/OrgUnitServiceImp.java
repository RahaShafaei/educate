package edu.educate.service;

import edu.educate.dto.OrgUnitDto;
import edu.educate.dto.PersonDto;
import edu.educate.model.OrgUnitEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orgUnitService")
public class OrgUnitServiceImp implements OrgUnitService{

    @Override
    public List<OrgUnitDto> getOrgUnits() {
        return null;
    }

    @Override
    public OrgUnitDto getOrgUnit(Integer id) {
        return null;
    }

    @Override
    public List<PersonDto> getOrgUnitPersons(Integer id) {
        return null;
    }

    @Override
    public Boolean deleteOrgUnit(Integer id) {
        return null;
    }

    @Override
    public OrgUnitDto createOrgUnit(OrgUnitEntity OrgUnit) {
        return null;
    }
}

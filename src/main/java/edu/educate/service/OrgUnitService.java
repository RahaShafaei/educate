package edu.educate.service;

import edu.educate.model.OrgUnitEntity;

import java.util.List;

public interface OrgUnitService {
    public List<OrgUnitEntity> getOrgUnits();

    public OrgUnitEntity getOrgUnit(Integer id);

    public Boolean deleteOrgUnit(Integer id);

    public OrgUnitEntity createOrgUnit(OrgUnitEntity orgUnit);
}

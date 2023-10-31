package edu.educate.service;

import edu.educate.model.OrgUnitPostPersonEntity;

import java.util.List;

public interface OrgUnitPostPersonService {
    public List<OrgUnitPostPersonEntity> getOrgUnitPostPersons();

    public OrgUnitPostPersonEntity getOrgUnitPostPerson(Integer id);

    public Boolean deleteOrgUnitPostPerson(Integer id);

    public OrgUnitPostPersonEntity createOrgUnitPostPerson(OrgUnitPostPersonEntity orgUnitPostPerson);
}

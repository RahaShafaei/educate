package edu.educate.service;

import edu.educate.dto.OrgUnitPostPersonDto;
import edu.educate.model.OrgUnitPostPersonEntity;

import java.util.List;

public interface OrgUnitPostPersonService {
    public List<OrgUnitPostPersonDto> getOrgUnitPostPersons();

    public OrgUnitPostPersonDto getOrgUnitPostPerson(Integer id);

    public Boolean deleteOrgUnitPostPerson(Integer id);

    public OrgUnitPostPersonDto createOrgUnitPostPerson(OrgUnitPostPersonEntity orgUnitPostPerson);
}

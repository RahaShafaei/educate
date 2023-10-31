package edu.educate.service;

import edu.educate.exception.ItemNotFoundException;
import edu.educate.exception.ParametersNotValidException;
import edu.educate.model.OrgUnitPostPersonEntity;
import edu.educate.repository.OrgUnitPostPersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service("orgUnitPostPersonService")
public class OrgUnitPostPersonServiceImp implements OrgUnitPostPersonService{

    private static final String ORGUNITPOSTPERSON_ID = "OrgUnitPostPerson id: ";
    private final OrgUnitPostPersonRepository orgUnitPostPersonRepository;
    @Override
    public List<OrgUnitPostPersonEntity> getOrgUnitPostPersons() {
        return orgUnitPostPersonRepository.findAll();
    }

    @Override
    public OrgUnitPostPersonEntity getOrgUnitPostPerson(Integer id) {
        Optional<OrgUnitPostPersonEntity> orgUnitPostPerson = orgUnitPostPersonRepository.findById(id);

        if (orgUnitPostPerson.isEmpty())
            throw new ItemNotFoundException(ORGUNITPOSTPERSON_ID + id);

        return orgUnitPostPerson.get();
    }

    @Override
    public Boolean deleteOrgUnitPostPerson(Integer id) {
        Optional<OrgUnitPostPersonEntity> orgUnitPostPerson = orgUnitPostPersonRepository.findById(id);

        if (!orgUnitPostPerson.isEmpty()) {
            orgUnitPostPersonRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public OrgUnitPostPersonEntity createOrgUnitPostPerson(OrgUnitPostPersonEntity orgUnitPostPerson) {
        if (orgUnitPostPerson.getOrgUnit() == null)
            throw new ParametersNotValidException("OrgUnit of OrgUnitPostPerson should not be empty.");

        if (orgUnitPostPerson.getOrgPost() == null)
            throw new ParametersNotValidException("OrgPost of OrgUnitPostPerson should not be empty.");

        if (orgUnitPostPerson.getPerson() == null )
            throw new ParametersNotValidException("Person of OrgUnitPostPerson should not be empty.");

        if (orgUnitPostPerson.getFromDate() == null )
            throw new ParametersNotValidException("FromDate of OrgUnitPostPerson should not be empty.");

        if (orgUnitPostPerson.getToDate() == null )
            throw new ParametersNotValidException("ToDate of OrgUnitPostPerson should not be empty.");

        OrgUnitPostPersonEntity savedOrgUnitPostPerson = orgUnitPostPersonRepository.save(orgUnitPostPerson);

        return savedOrgUnitPostPerson;
    }
}

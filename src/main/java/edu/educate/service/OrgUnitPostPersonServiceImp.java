package edu.educate.service;

import edu.educate.exception.ItemNotFoundException;
import edu.educate.model.OrgUnitPostPersonEntity;
import edu.educate.repository.OrgUnitPostPersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service("orgUnitPostPersonService")
public class OrgUnitPostPersonServiceImp implements OrgUnitPostPersonService{

    private static final String ORGUNITPOSTPERSON_ID = "OrgUnitPostPerson id: ";
    private final OrgUnitPostPersonRepository orgUnitPostPersonRepository;
    @Override
    public List<OrgUnitPostPersonEntity> getOrgUnitPostPersons() {
        return orgUnitPostPersonRepository.findByDeletedFalse();
    }

    @Override
    public OrgUnitPostPersonEntity getOrgUnitPostPerson(Integer id) {
        Optional<OrgUnitPostPersonEntity> orgUnitPostPerson = orgUnitPostPersonRepository.findByIdAndDeletedFalse(id);

        if (orgUnitPostPerson.isEmpty())
            throw new ItemNotFoundException(ORGUNITPOSTPERSON_ID + id);

        return orgUnitPostPerson.get();
    }

    @Override
    public Boolean deleteOrgUnitPostPerson(Integer id) {
        Optional<OrgUnitPostPersonEntity> orgUnitPostPerson = orgUnitPostPersonRepository.findByIdAndDeletedFalse(id);

        if (!orgUnitPostPerson.isEmpty()) {
            orgUnitPostPerson.get().setDeleted(true);
            orgUnitPostPerson.get().setDeletedAt(LocalDateTime.now());
            orgUnitPostPersonRepository.save(orgUnitPostPerson.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public OrgUnitPostPersonEntity createOrgUnitPostPerson(  OrgUnitPostPersonEntity orgUnitPostPerson) {

        OrgUnitPostPersonEntity savedOrgUnitPostPerson = orgUnitPostPersonRepository.save(orgUnitPostPerson);

        return savedOrgUnitPostPerson;
    }
}

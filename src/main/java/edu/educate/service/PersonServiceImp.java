package edu.educate.service;

import edu.educate.dto.PersonDto;
import edu.educate.dto.baseDto.BaseDto;
import edu.educate.exception.ParametersNotValidException;
import edu.educate.model.ElementEntity;
import edu.educate.model.OrgUnitPostPersonEntity;
import edu.educate.model.PersonEntity;
import edu.educate.model.RolesEntity;
import edu.educate.repository.PersonRepository;
import edu.educate.service.baseService.GenericServiceImpl;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service("personService")
public class PersonServiceImp extends GenericServiceImpl<PersonEntity,PersonDto> implements PersonService {
    @Autowired
    private OrgUnitPostPersonService orgUnitPostPersonService;

    @Autowired
    private RolesService rolesService;

    @Autowired
    public PersonServiceImp(PersonRepository repository) {
        super(repository, "PersonEntity");
    }

    @Override
    public boolean entityValidation(BaseDto baseDto) {
        PersonEntity personEntity = ((PersonDto) baseDto).getPersonWrapper();
        OrgUnitPostPersonEntity orgUnitPostPersonEntity = ((PersonDto) baseDto).getOrgUnitPostPersonWrapper();

        return checkDateRange(personEntity, orgUnitPostPersonEntity);
    }

    @Override
    public BaseDto getEntityByRelatedEntities(Integer id) {
        PersonEntity personEntity = getEntity(id);
        OrgUnitPostPersonEntity orgUnitPostPerson = getLastPost(personEntity);

        PersonDto person = new PersonDto();
        person.setPersonWrapper(personEntity);
        person.setOrgUnitPostPersonWrapper(orgUnitPostPerson);

        return person;
    }

    @Override
    public BaseDto createEntityByRelatedEntities(BaseDto baseDto) {
        PersonEntity personEntity = ((PersonDto) baseDto).getPersonWrapper();
        OrgUnitPostPersonEntity orgUnitPostPersonEntity = ((PersonDto) baseDto).getOrgUnitPostPersonWrapper();

        updatePersonRoles(((PersonDto) baseDto).getRolesWrapper(), personEntity);
        updateOrgUnitPersons(orgUnitPostPersonEntity, personEntity);

        PersonEntity savedPersonEntity = super.createEntity(personEntity);
        orgUnitPostPersonService.createEntity(orgUnitPostPersonEntity);

        ((PersonDto) baseDto).setPersonWrapper(savedPersonEntity);
        ((PersonDto) baseDto).setOrgUnitPostPersonWrapper(null);
        ((PersonDto) baseDto).setRolesWrapper(null);

        return baseDto;
    }

    @Override
    public List<PersonEntity> findEntitiesBySpecificFields(PersonEntity personEntity) {
        ExampleMatcher SEARCH_CONDITIONS_MATCH_ALL = ExampleMatcher
                .matching()
                .withMatcher("personRoles.id", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("deleted", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withIgnoreNullValues()
                .withIgnorePaths("id", "deletedAt", "insertedAt",
                        "fname", "lname", "fatherName", "nlCode", "prCode", "tel"
                );
        personEntity.setDeleted(false);

        Example<PersonEntity> example = Example.of(personEntity, SEARCH_CONDITIONS_MATCH_ALL);

        return findEntities(example);
    }

    private OrgUnitPostPersonEntity getLastPost(PersonEntity personEntity) {
        return personEntity.getOrgUnitPostPersons().stream()
                .filter(el -> el.getLtToDate() == null)
                .findFirst()
                .orElse(null);
    }

    private LocalDateTime getLastToDate(PersonEntity personEntity) {
        return personEntity.getOrgUnitPostPersons().stream()
                .map(OrgUnitPostPersonEntity::getLtToDate)
                .filter(Objects::nonNull)
                .max(Comparator.naturalOrder())
                .orElse(null);
    }

    private boolean checkDateRange(PersonEntity personEntity, OrgUnitPostPersonEntity orgUnitPostPersonEntity) {
        if (personEntity.getOrgUnitPostPersons() == null || getLastToDate(personEntity) == null)
            return true;

        if (orgUnitPostPersonEntity.getLtFromDate() == null)
            return false;

        long cnt = personEntity.getOrgUnitPostPersons().stream()
                .filter(e -> e.getLtToDate() != null)
                .filter(e -> e.getLtToDate().isAfter(orgUnitPostPersonEntity.getLtFromDate()))
                .count();

        return cnt <= 0;
    }

    private void updatePersonRoles(List<Integer> rolesId, PersonEntity personEntity) {
        List<RolesEntity> newRoles = rolesService.getAllEntitiesByIds(rolesId);

        if (personEntity.getPersonRoles() == null)
            personEntity.setPersonRoles(new ArrayList<>());

        personEntity.getPersonRoles().removeIf(role -> !newRoles.contains(role));

        newRoles.stream()
                .filter(role -> !personEntity.getPersonRoles().contains(role))
                .forEach(role -> personEntity.getPersonRoles().add(role));
    }

    public void updateOrgUnitPersons(OrgUnitPostPersonEntity orgUnitPostPersonEntity, PersonEntity personEntity) {

        if (personEntity.getOrgUnitPostPersons() == null)
            personEntity.setOrgUnitPostPersons(new ArrayList<>());

        if (orgUnitPostPersonEntity.getId() == null) {
            orgUnitPostPersonEntity.setPerson(personEntity);
            personEntity.getOrgUnitPostPersons().add(orgUnitPostPersonEntity);
            return;
        }

        Optional<OrgUnitPostPersonEntity> newOrgUnitPostPersonEntity = orgUnitPostPersonService.getEntityById(orgUnitPostPersonEntity.getId());

        if (personEntity.getOrgUnitPostPersons().contains(newOrgUnitPostPersonEntity.get())) {
            List<OrgUnitPostPersonEntity> orgUnitPostPersonEntities = personEntity.getOrgUnitPostPersons().stream()
                    .map(el -> el.getId() == orgUnitPostPersonEntity.getId() ? orgUnitPostPersonEntity : el)
                    .toList();
            personEntity.setOrgUnitPostPersons(orgUnitPostPersonEntities);
        }
    }

}
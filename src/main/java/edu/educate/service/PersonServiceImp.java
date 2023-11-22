package edu.educate.service;

import edu.educate.dto.PersonDto;
import edu.educate.dto.baseDto.BaseDto;
import edu.educate.exception.ParametersNotValidException;
import edu.educate.model.OrgUnitPostPersonEntity;
import edu.educate.model.PersonEntity;
import edu.educate.model.RolesEntity;
import edu.educate.repository.PersonRepository;
import edu.educate.service.baseService.GenericServiceImpl;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private OrgUnitPostPersonEntity getLastPost(PersonEntity personEntity) {
        return personEntity.getOrgUnitPostPersons().stream()
                .filter(el -> el.getToDate() == null)
                .findFirst()
                .orElse(null);
    }

    private boolean checkDateRange(PersonEntity personEntity, OrgUnitPostPersonEntity orgUnitPostPersonEntity) {
        if (personEntity.getOrgUnitPostPersons() == null || getLastPost(personEntity) != null)
            return true;

        long cnt = personEntity.getOrgUnitPostPersons().stream()
                .filter(e -> e.getToDate().isAfter(orgUnitPostPersonEntity.getFromDate()))
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

//    private void updatePersonRoles(PersonEntity person, List<Integer> ids, RolesService rolesService) {
//        List<RolesEntity> newRoles = rolesService.getAllEntitiesByIds(ids);
//
//        person.getPersonRoles().removeIf(role -> !newRoles.contains(role.getRole()));
//
//        newRoles.stream()
//                .filter(role -> !person.getRoles().contains(role))
//                .map(role -> {
//                    PersonRoleEntity personRoleEntity = new PersonRoleEntity();
//                    personRoleEntity.setPerson(person);
//                    personRoleEntity.setRole(role);
//                    return personRoleEntity;
//                })
//                .forEach(personRoleEntity -> person.getPersonRoles().add(personRoleEntity));
//    }

}
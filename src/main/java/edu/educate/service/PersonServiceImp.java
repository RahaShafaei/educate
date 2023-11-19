package edu.educate.service;

import edu.educate.model.PersonEntity;
import edu.educate.model.PersonRoleEntity;
import edu.educate.model.RolesEntity;
import edu.educate.repository.PersonRepository;
import edu.educate.service.baseService.GenericService;
import edu.educate.service.baseService.GenericServiceImpl;
import edu.educate.service.baseService.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("personService")
public class PersonServiceImp extends GenericServiceImpl<PersonEntity> implements PersonService {

    @Autowired
    public PersonServiceImp(PersonRepository repository) {
        super(repository, "PersonEntity");
    }

    @Override
    public PersonEntity createEntityByRelatedIds(PersonEntity person, List<Integer> ids, Map<String, MainService> serviceMap) {
        if (person.getPersonRoles() == null)
            person.setPersonRoles(new ArrayList<>());

        updatePersonRoles(person, ids, (RolesService) serviceMap.get("RolesService"));

        super.createEntity(person);
        return null;
    }

    private void updatePersonRoles(PersonEntity person, List<Integer> ids, RolesService rolesService) {
        List<RolesEntity> newRoles = rolesService.getAllEntitiesByIds(ids);

        person.getPersonRoles().removeIf(role -> !newRoles.contains(role));

        newRoles.stream()
                .filter(role -> !person.getPersonRoles().contains(role))
                .forEach(role -> person.getPersonRoles().add(role));
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
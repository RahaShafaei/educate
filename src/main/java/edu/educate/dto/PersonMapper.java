package edu.educate.dto;

import edu.educate.helper.ConvertListToMap;
import edu.educate.model.*;
import edu.educate.model.PersonEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
@AllArgsConstructor
@Component
public class PersonMapper {

    private final OrgUnitPostPersonMapper orgUnitPostPersonMapper;

    private final PersonRoleMapper personRoleMapper;

    public PersonDto toDto(PersonEntity person) {

        PersonDto personDto = new PersonDto();

        personDto.setId(person.getId());
        personDto.setDeleted(person.getDeleted());
        personDto.setDeletedAt(person.getDeletedAt());
        personDto.setInsertedAt(person.getInsertedAt());

        personDto.setFname(person.getFname());
        personDto.setLname(person.getLname());
        personDto.setFatherName(person.getFatherName());
        personDto.setNlCode(person.getNlCode());
        personDto.setPrCode(person.getPrCode());
        personDto.setTel(person.getTel());

        if (person.getOrgUnitPostPersons() != null) {
            Map<Integer, OrgUnitPostPersonDto> orgUnitPostPersonDtoDtl = ConvertListToMap.apply(
                    person.getOrgUnitPostPersons(),
                    OrgUnitPostPersonEntity::getId,
                    orgUnitPostPersonMapper::toDto
            );
            personDto.setOrgUnitPostPersons(orgUnitPostPersonDtoDtl);
        }
        if (person.getPersonRoles() != null) {
            Map<Integer, PersonRoleDto> personRoleDtl = ConvertListToMap.apply(
                    person.getPersonRoles(),
                    PersonRoleEntity::getId,
                    personRoleMapper::toDto
            );
            personDto.setPersonRoles(personRoleDtl);
        }

        return personDto;
    }
}

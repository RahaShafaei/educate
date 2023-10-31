package edu.educate.dto;

import edu.educate.model.PersonRoleEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PersonRoleMapper {

    private final PersonMapper personMapper;

    private final RolesMapper rolesMapper;

    public PersonRoleDto toDto(PersonRoleEntity personRole) {

        PersonRoleDto personRoleDto = new PersonRoleDto();

        personRoleDto.setId(personRole.getId());
        personRoleDto.setDeleted(personRole.getDeleted());
        personRoleDto.setDeletedAt(personRole.getDeletedAt());
        personRoleDto.setInsertedAt(personRole.getInsertedAt());

        personRoleDto.setPerson(
                personRole.getPerson() != null ?
                        this.personMapper.toDto(personRole.getPerson()) :
                        null
        );
        personRoleDto.setRole(
                personRole.getRole() != null ?
                        this.rolesMapper.toDto(personRole.getRole()) :
                        null
        );

        return personRoleDto;
    }
}
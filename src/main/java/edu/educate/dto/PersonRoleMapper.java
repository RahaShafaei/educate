package edu.educate.dto;

import edu.educate.dto.baseDto.DtoMapperUtils;
import edu.educate.model.PersonRoleEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PersonRoleMapper {
    private final DtoMapperUtils dtoMapperUtils;

    private final PersonMapper personMapper;

    private final RolesMapper rolesMapper;

    public PersonRoleDto toDto(PersonRoleEntity personRole) {

        PersonRoleDto personRoleDto = new PersonRoleDto();

        dtoMapperUtils.populateCommonFields(personRole, personRoleDto);

        personRoleDto.setPerson(this.personMapper.toDto(personRole.getPerson()));
        personRoleDto.setRole(this.rolesMapper.toDto(personRole.getRole()));

        return personRoleDto;
    }
}
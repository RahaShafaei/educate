package edu.educate.dto;

import edu.educate.helper.ConvertListToMap;
import edu.educate.model.PersonRoleEntity;
import edu.educate.model.RolesEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@AllArgsConstructor
@Component
public class RolesMapper {
    private final PersonRoleMapper personRoleMapper;

    public RolesDto toDto(RolesEntity role) {
        RolesDto rolesDto = new RolesDto();

        rolesDto.setId(role.getId());
        rolesDto.setTitle(role.getTitle());
        rolesDto.setDeleted(role.getDeleted());
        rolesDto.setDeletedAt(role.getDeletedAt());
        rolesDto.setInsertedAt(role.getInsertedAt());

        if (role.getPersonRoles() != null) {
            Map<Integer, PersonRoleDto> personRoleDtl = ConvertListToMap.apply(
                    role.getPersonRoles(),
                    PersonRoleEntity::getId,
                    personRoleMapper::toDto
            );
            rolesDto.setPersonRoles(personRoleDtl);
        }

        return rolesDto;
    }
}

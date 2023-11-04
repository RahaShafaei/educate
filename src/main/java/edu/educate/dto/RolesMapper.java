package edu.educate.dto;

import edu.educate.model.RolesEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RolesMapper {

    public RolesDto toDto(RolesEntity role) {
        RolesDto rolesDto = new RolesDto();

        rolesDto.setId(role.getId());
        rolesDto.setTitle(role.getTitle());
        rolesDto.setDeleted(role.isDeleted());
        rolesDto.setDeletedAt(role.getDeletedAt());
        rolesDto.setInsertedAt(role.getInsertedAt());

        return rolesDto;
    }
}

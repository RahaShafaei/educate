package edu.educate.dto;

import edu.educate.dto.baseDto.DtoMapperUtils;
import edu.educate.model.RolesEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RolesMapper {
    private final DtoMapperUtils dtoMapperUtils;
    public RolesDto toDto(RolesEntity role) {
        RolesDto rolesDto = new RolesDto();

        dtoMapperUtils.populateCommonFieldsLp(role, rolesDto);

        return rolesDto;
    }
}

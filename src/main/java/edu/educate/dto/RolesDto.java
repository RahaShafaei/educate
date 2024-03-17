package edu.educate.dto;

import edu.educate.dto.baseDto.TitleLPDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class RolesDto extends TitleLPDto {

    private Map<Integer, PersonRoleDto> personRoles;

}

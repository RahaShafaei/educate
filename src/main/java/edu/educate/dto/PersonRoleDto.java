package edu.educate.dto;

import edu.educate.dto.baseDto.BaseDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PersonRoleDto extends BaseDto {
    private PersonDto person;

    private RolesDto role;
}

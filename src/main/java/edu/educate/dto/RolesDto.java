package edu.educate.dto;

import edu.educate.dto.baseDto.TitleDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class RolesDto extends TitleDto {

    private Map<Integer, PersonRoleDto> personRoles;

}

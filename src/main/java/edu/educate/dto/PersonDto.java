package edu.educate.dto;

import edu.educate.dto.baseDto.BaseDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PersonDto extends BaseDto {
    private List<OrgUnitPostPersonDto> orgUnitPostPersons;

    private List<PersonRoleDto> personRoles;

    private String fname;

    private String lname;

    private String fatherName;

    private String nlCode;

    private String prCode;

    private String tel;
}

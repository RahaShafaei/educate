package edu.educate.dto;

import edu.educate.dto.baseDto.BaseDto;
import edu.educate.model.OrgUnitPostPersonEntity;
import edu.educate.model.PersonEntity;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@Validated
public class PersonDto extends BaseDto {

    private Map<Integer, OrgUnitPostPersonDto> orgUnitPostPersons;

    private Map<Integer, PersonRoleDto> personRoles;

    private String fname;

    private String lname;

    private String fatherName;

    private String nlCode;

    private String prCode;

    private String tel;

    /*
    * Fields for controller wrapper
    * */
    @Valid
    private OrgUnitPostPersonEntity orgUnitPostPersonWrapper;

    @Valid
    private PersonEntity personWrapper;

    private List<Integer> rolesWrapper;
}

package edu.educate.dto;

import edu.educate.dto.baseDto.TitleDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OrgUnitDto extends TitleDto {
    private List<OrgUnitDto> orgUnit;

    private OrgUnitDto parentOrgUnit;

    private List<PlansDto> plans;

    private List<OrgUnitPostPersonDto> orgUnitPostPersons;

    private String code;

    private String descr;
}

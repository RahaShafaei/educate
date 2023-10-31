package edu.educate.dto;

import edu.educate.dto.baseDto.TitleDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class OrgUnitDto extends TitleDto {

    private List<Integer> orgUnits;

    private Integer parentOrgUnit;

    private Map<Integer, PlansDto> plans;

    private Map<Integer, OrgUnitPostPersonDto> orgUnitPostPersons;

    private String code;

    private String descr;
}

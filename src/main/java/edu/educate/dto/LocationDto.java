package edu.educate.dto;

import edu.educate.dto.baseDto.TitleDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class LocationDto extends TitleDto {
    private Map<Integer, OrgUnitDto> orgUnits;

    private Map<Integer, PlansDto> plans;

    private String code;

    private String descr;
}

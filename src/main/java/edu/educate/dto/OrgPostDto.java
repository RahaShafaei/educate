package edu.educate.dto;

import edu.educate.dto.baseDto.TitleDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class OrgPostDto extends TitleDto {

    private Map<Integer, OrgUnitPostPersonDto> orgUnitPostPersons;

    private String code;

    private String descr;
}

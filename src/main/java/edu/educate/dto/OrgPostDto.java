package edu.educate.dto;

import edu.educate.dto.baseDto.TitleDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OrgPostDto extends TitleDto {
    private List<OrgUnitPostPersonDto> orgUnitPostPersons;

    private String code;

    private String descr;
}

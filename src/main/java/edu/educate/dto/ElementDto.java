package edu.educate.dto;

import edu.educate.dto.baseDto.TitleLPDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class ElementDto extends TitleLPDto {

    private ElementGrpDto elementGrp;

    private Map<Integer, AttendanceDto> attendances;

    private Map<Integer, PlansDto> planTypes;

    private Map<Integer, PlansDto> planStatus;
}

package edu.educate.dto;

import edu.educate.dto.baseDto.TitleDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ElementDto extends TitleDto {
    private ElementGrpDto elementGrp;

    private List<AttendanceDto> attendances;

    private List<PlansDto> planTypes;

    private List<PlansDto> planStatus;
}

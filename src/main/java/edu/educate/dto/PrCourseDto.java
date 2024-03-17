package edu.educate.dto;

import edu.educate.dto.baseDto.TitleLPDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class PrCourseDto extends TitleLPDto {
    private PrCourseGrpDto prCourseGrp;

    private Map<Integer, PlansDto> plans;

    private String descr;
}

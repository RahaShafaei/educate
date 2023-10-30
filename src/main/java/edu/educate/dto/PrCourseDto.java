package edu.educate.dto;

import edu.educate.dto.baseDto.BaseDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PrCourseDto extends BaseDto {
    private PrCourseGrpDto prCourseGrp;

    private List<PlansDto> prCourses;

    private String ltTitle;

    private String prTitle;

    private String descr;
}

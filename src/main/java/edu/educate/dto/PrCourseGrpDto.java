package edu.educate.dto;

import edu.educate.dto.baseDto.BaseDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PrCourseGrpDto  extends BaseDto {
    private List<PrCourseDto> prCourses;

    private String ltTitle;

    private String prTitle;

    private String descr;
}

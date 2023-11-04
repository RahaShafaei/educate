package edu.educate.dto;

import edu.educate.dto.baseDto.DtoMapperUtils;
import edu.educate.model.PrCourseEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
@AllArgsConstructor
@Component
public class PrCourseMapper {
    private final DtoMapperUtils dtoMapperUtils;

    public PrCourseDto toDto(PrCourseEntity prCourse) {

        PrCourseDto prCourseDto = new PrCourseDto();

        dtoMapperUtils.populateCommonFields(prCourse, prCourseDto);

        prCourseDto.setLtTitle(prCourse.getLtTitle());
        prCourseDto.setPrTitle(prCourse.getPrTitle());
        prCourseDto.setDescr(prCourse.getDescr());

        return prCourseDto;
    }
}

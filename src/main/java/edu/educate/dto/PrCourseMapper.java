package edu.educate.dto;

import edu.educate.model.PrCourseEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
@AllArgsConstructor
@Component
public class PrCourseMapper {

    public PrCourseDto toDto(PrCourseEntity prCourse) {

        PrCourseDto prCourseDto = new PrCourseDto();

        prCourseDto.setId(prCourse.getId());
        prCourseDto.setDeleted(prCourse.isDeleted());
        prCourseDto.setDeletedAt(prCourse.getDeletedAt());
        prCourseDto.setInsertedAt(prCourse.getInsertedAt());

        prCourseDto.setLtTitle(prCourse.getLtTitle());
        prCourseDto.setPrTitle(prCourse.getPrTitle());
        prCourseDto.setDescr(prCourse.getDescr());

        return prCourseDto;
    }
}

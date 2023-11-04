package edu.educate.dto;

import edu.educate.helper.ConvertListToMap;
import edu.educate.model.AttendanceEntity;
import edu.educate.model.PrCourseEntity;
import edu.educate.model.PrCourseGrpEntity;
import edu.educate.model.PlansEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
@AllArgsConstructor
@Component
public class PrCourseGrpMapper {
    private final PrCourseMapper prCourseMapper;

    public PrCourseGrpDto toDto(PrCourseGrpEntity prCourseGrp) {

        PrCourseGrpDto prCourseGrpDto = new PrCourseGrpDto();

        prCourseGrpDto.setId(prCourseGrp.getId());
        prCourseGrpDto.setDeleted(prCourseGrp.isDeleted());
        prCourseGrpDto.setDeletedAt(prCourseGrp.getDeletedAt());
        prCourseGrpDto.setInsertedAt(prCourseGrp.getInsertedAt());

        prCourseGrpDto.setLtTitle(prCourseGrp.getLtTitle());
        prCourseGrpDto.setPrTitle(prCourseGrp.getPrTitle());
        prCourseGrpDto.setDescr(prCourseGrp.getDescr());

        if (prCourseGrp.getPrCourses() != null) {
            Map<Integer, PrCourseDto> prCourseDtl = ConvertListToMap.apply(
                    prCourseGrp.getPrCourses(),
                    PrCourseEntity::getId,
                    prCourseMapper::toDto
            );
            prCourseGrpDto.setPrCourses(prCourseDtl);
        }

        return prCourseGrpDto;
    }
}

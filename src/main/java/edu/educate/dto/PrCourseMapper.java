package edu.educate.dto;

import edu.educate.helper.ConvertListToMap;
import edu.educate.model.*;
import edu.educate.model.PrCourseEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
@AllArgsConstructor
@Component
public class PrCourseMapper {
    
    private final PlansMapper planMapper;

    private final PrCourseGrpMapper prCourseGrpMapper;

    public PrCourseDto toDto(PrCourseEntity prCourse) {

        PrCourseDto prCourseDto = new PrCourseDto();

        prCourseDto.setId(prCourse.getId());
        prCourseDto.setDeleted(prCourse.getDeleted());
        prCourseDto.setDeletedAt(prCourse.getDeletedAt());
        prCourseDto.setInsertedAt(prCourse.getInsertedAt());

        prCourseDto.setLtTitle(prCourse.getLtTitle());
        prCourseDto.setPrTitle(prCourse.getPrTitle());
        prCourseDto.setDescr(prCourse.getDescr());

        prCourseDto.setPrCourseGrp(
                prCourse.getPrCourseGrp() != null ?
                        this.prCourseGrpMapper.toDto(prCourse.getPrCourseGrp()) :
                        null
        );
        if (prCourse.getPlans() != null) {
            Map<Integer, PlansDto> planStatusDtl = ConvertListToMap.apply(
                    prCourse.getPlans(),
                    PlansEntity::getId,
                    planMapper::toDto
            );
            prCourseDto.setPlans(planStatusDtl);
        }

        return prCourseDto;
    }
}

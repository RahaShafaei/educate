package edu.educate.dto;

import edu.educate.helper.ConvertListToMap;
import edu.educate.model.AttendanceEntity;
import edu.educate.model.ElementEntity;
import edu.educate.model.PlansEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@AllArgsConstructor
@Component
public class ElementMapper {
    private final ElementGrpMapper elementGrpMapper;

    private final AttendanceMapper attendanceMapper;

    private final PlansMapper planMapper;

    public ElementDto toDto(ElementEntity element) {

        ElementDto elementDto = new ElementDto();

        elementDto.setId(element.getId());
        elementDto.setDeleted(element.getDeleted());
        elementDto.setDeletedAt(element.getDeletedAt());
        elementDto.setInsertedAt(element.getInsertedAt());

        elementDto.setElementGrp(
                element.getElementGrp() != null ?
                        this.elementGrpMapper.toDto(element.getElementGrp()) :
                        null
        );
        if (element.getAttendances() != null) {
            Map<Integer, AttendanceDto> attendanceDtl = ConvertListToMap.apply(
                    element.getAttendances(),
                    AttendanceEntity::getId,
                    attendanceMapper::toDto
            );
            elementDto.setAttendances(attendanceDtl);
        }
        if (element.getPlanStatus() != null) {
            Map<Integer, PlansDto> planStatusDtl = ConvertListToMap.apply(
                    element.getPlanStatus(),
                    PlansEntity::getId,
                    planMapper::toDto
            );
            elementDto.setPlanStatus(planStatusDtl);
        }
        if (element.getPlanTypes() != null) {
            Map<Integer, PlansDto> planTypesDtl = ConvertListToMap.apply(
                    element.getPlanTypes(),
                    PlansEntity::getId,
                    planMapper::toDto
            );
            elementDto.setPlanTypes(planTypesDtl);
        }

        return elementDto;
    }
}

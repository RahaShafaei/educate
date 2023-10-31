package edu.educate.dto;

import edu.educate.helper.ConvertListToMap;
import edu.educate.model.AttendanceEntity;
import edu.educate.model.ElementEntity;
import edu.educate.model.ElementGrpEntity;
import edu.educate.model.PlansEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
@AllArgsConstructor
@Component
public class ElementGrpMapper {
    private final ElementMapper elementMapper;
    public ElementGrpDto toDto(ElementGrpEntity elementGrp) {

        ElementGrpDto elementGrpDto = new ElementGrpDto();

        elementGrpDto.setId(elementGrp.getId());
        elementGrpDto.setDeleted(elementGrp.getDeleted());
        elementGrpDto.setDeletedAt(elementGrp.getDeletedAt());
        elementGrpDto.setInsertedAt(elementGrp.getInsertedAt());

        if (elementGrp.getElements() != null) {
            Map<Integer, ElementDto> attendanceDtl = ConvertListToMap.apply(
                    elementGrp.getElements(),
                    ElementEntity::getId,
                    elementMapper::toDto
            );
            elementGrpDto.setElements(attendanceDtl);
        }

        return elementGrpDto;
    }
}

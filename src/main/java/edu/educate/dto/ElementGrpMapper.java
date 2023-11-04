package edu.educate.dto;

import edu.educate.dto.baseDto.DtoMapperUtils;
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
    private final DtoMapperUtils dtoMapperUtils;
    private final ElementMapper elementMapper;
    public ElementGrpDto toDto(ElementGrpEntity elementGrp) {

        ElementGrpDto elementGrpDto = new ElementGrpDto();

        dtoMapperUtils.populateCommonFields(elementGrp, elementGrpDto);

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

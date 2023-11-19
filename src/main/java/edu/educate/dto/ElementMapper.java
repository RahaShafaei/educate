package edu.educate.dto;

import edu.educate.dto.baseDto.DtoMapperUtils;
import edu.educate.model.ElementEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ElementMapper {
    private final DtoMapperUtils dtoMapperUtils;
    public ElementDto toDto(ElementEntity element) {

        ElementDto elementDto = new ElementDto();

        dtoMapperUtils.populateCommonFieldsLp(element, elementDto);

        return elementDto;
    }
}

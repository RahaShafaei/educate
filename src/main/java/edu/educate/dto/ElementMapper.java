package edu.educate.dto;

import edu.educate.model.ElementEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ElementMapper {
    public ElementDto toDto(ElementEntity element) {

        ElementDto elementDto = new ElementDto();

        elementDto.setId(element.getId());
        elementDto.setDeleted(element.getDeleted());
        elementDto.setDeletedAt(element.getDeletedAt());
        elementDto.setInsertedAt(element.getInsertedAt());

        elementDto.setTitle(element.getTitle());

        return elementDto;
    }
}

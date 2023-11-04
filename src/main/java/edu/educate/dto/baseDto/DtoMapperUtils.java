package edu.educate.dto.baseDto;

import edu.educate.model.baseModel.BaseEntity;
import org.springframework.stereotype.Component;

@Component
public class DtoMapperUtils {
    public void populateCommonFields(BaseEntity source, BaseDto target) {
        target.setId(source.getId());
        target.setDeleted(source.isDeleted());
        target.setDeletedAt(source.getDeletedAt());
        target.setInsertedAt(source.getInsertedAt());
    }
}


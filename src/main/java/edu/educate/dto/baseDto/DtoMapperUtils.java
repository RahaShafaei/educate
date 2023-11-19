package edu.educate.dto.baseDto;

import edu.educate.model.baseModel.BaseEntity;
import edu.educate.model.baseModel.TitleLPEntity;
import org.springframework.stereotype.Component;

@Component
public class DtoMapperUtils {
    public void populateCommonFields(BaseEntity source, BaseDto target) {
        target.setId(source.getId());
        target.setDeleted(source.isDeleted());
        target.setDeletedAt(source.getDeletedAt());
        target.setInsertedAt(source.getInsertedAt());
    }

    public void populateCommonFieldsLp(BaseEntity source, BaseDto target) {
        target.setId(source.getId());
        target.setDeleted(source.isDeleted());
        target.setDeletedAt(source.getDeletedAt());
        target.setInsertedAt(source.getInsertedAt());
        ((TitleLPDto) target).setLtTitle(((TitleLPEntity) source).getLtTitle());
        ((TitleLPDto) target).setPrTitle(((TitleLPEntity) source).getPrTitle());
    }
}


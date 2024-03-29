package edu.educate.service;

import edu.educate.dto.ElementDto;
import edu.educate.model.ElementEntity;
import edu.educate.service.baseService.GenericService;

import java.util.List;

public interface ElementService extends GenericService<ElementEntity, ElementDto> {
    List<ElementEntity> findByLtTitleNot();
    List<ElementEntity> findByElementGrpLtTitle(String title);

}

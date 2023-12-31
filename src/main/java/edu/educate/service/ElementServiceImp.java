package edu.educate.service;

import edu.educate.dto.ElementDto;
import edu.educate.model.ElementEntity;
import edu.educate.model.baseModel.BaseEntity;
import edu.educate.repository.ElementRepository;
import edu.educate.service.baseService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("elementService")
public class ElementServiceImp extends GenericServiceImpl<ElementEntity, ElementDto> implements ElementService {

    @Autowired
    public ElementServiceImp(ElementRepository repository) {
        super(repository, "ElementEntity");
    }

    @Override
    public List<ElementEntity> findEntitiesBySpecificFields(ElementEntity elementEntity) {
        ExampleMatcher SEARCH_CONDITIONS_MATCH_ALL = ExampleMatcher
                .matching()
                .withMatcher("elementGrp.id", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("deleted", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withIgnoreNullValues()
                .withIgnorePaths("id", "deletedAt", "insertedAt", "title");
        elementEntity.setDeleted(false);

        Example<ElementEntity> example = Example.of(elementEntity, SEARCH_CONDITIONS_MATCH_ALL);

        return findEntities(example);
    }
}
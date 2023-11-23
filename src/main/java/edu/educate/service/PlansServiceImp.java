package edu.educate.service;

import edu.educate.dto.PlansDto;
import edu.educate.model.ElementEntity;
import edu.educate.model.PlansEntity;
import edu.educate.repository.PlansRepository;
import edu.educate.service.baseService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("plansService")
public class PlansServiceImp extends GenericServiceImpl<PlansEntity, PlansDto> implements PlansService {

    @Autowired
    public PlansServiceImp(PlansRepository repository) {
        super(repository, "PlansEntity");
    }

    @Override
    public List<PlansEntity> findEntitiesBySpecificFields(PlansEntity plansEntity) {
        ExampleMatcher SEARCH_CONDITIONS_MATCH_ALL = ExampleMatcher
                .matching()
                .withMatcher("elementStatus.id", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("deleted", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withIgnoreNullValues()
                .withIgnorePaths("id", "deletedAt", "insertedAt", "title");
        plansEntity.setDeleted(false);

        Example<PlansEntity> example = Example.of(plansEntity, SEARCH_CONDITIONS_MATCH_ALL);

        return findEntities(example);
    }
}
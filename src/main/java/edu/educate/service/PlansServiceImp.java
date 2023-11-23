package edu.educate.service;

import edu.educate.dto.PlansDto;
import edu.educate.model.ElementEntity;
import edu.educate.model.PlansEntity;
import edu.educate.repository.PlansRepository;
import edu.educate.repository.baseRepository.GenericRepository;
import edu.educate.service.baseService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("plansService")
public class PlansServiceImp extends GenericServiceImpl<PlansEntity, PlansDto> implements PlansService {
    @Autowired
    private ElementService elementService;
    @Autowired
    public PlansServiceImp(PlansRepository repository) {
        super(repository, "PlansEntity");
    }

    @Override
    public List<PlansEntity> findEntitiesBySpecificFields(PlansEntity plansEntity) {
        List<ElementEntity> elementEntities =new ArrayList<>();
        elementEntities.add(elementService.getEntityById(plansEntity.getElementStatus().getId()).get());

        return ((PlansRepository)this.repository).findByElementStatusNotIn(elementEntities);
    }
}
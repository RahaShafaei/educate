package edu.educate.service;

import edu.educate.dto.PlansDto;
import edu.educate.model.PlansEntity;
import edu.educate.repository.PlansRepository;
import edu.educate.service.baseService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("plansService")
public class PlansServiceImp extends GenericServiceImpl<PlansEntity, PlansDto> implements PlansService {

    @Autowired
    public PlansServiceImp(PlansRepository repository) {
        super(repository, "PlansEntity");
    }
}
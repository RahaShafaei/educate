package edu.educate.service;

import edu.educate.dto.RolesDto;
import edu.educate.model.RolesEntity;
import edu.educate.repository.RolesRepository;
import edu.educate.service.baseService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("rolesService")
public class RolesServiceImp extends GenericServiceImpl<RolesEntity, RolesDto> implements RolesService {

    @Autowired
    public RolesServiceImp(RolesRepository repository) {
        super(repository, "RolesEntity");
    }
}
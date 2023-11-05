package edu.educate.service;

import edu.educate.model.OrgUnitEntity;
import edu.educate.repository.OrgUnitRepository;
import edu.educate.service.baseService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orgUnitService")
public class OrgUnitServiceImp extends GenericServiceImpl<OrgUnitEntity> implements OrgUnitService {

    @Autowired
    public OrgUnitServiceImp(OrgUnitRepository repository) {
        super(repository, "OrgUnitEntity");
    }
}
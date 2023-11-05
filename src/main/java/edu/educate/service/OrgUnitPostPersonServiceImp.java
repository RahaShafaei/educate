package edu.educate.service;

import edu.educate.model.OrgUnitPostPersonEntity;
import edu.educate.repository.OrgUnitPostPersonRepository;
import edu.educate.service.baseService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orgUnitPostPersonService")
public class OrgUnitPostPersonServiceImp extends GenericServiceImpl<OrgUnitPostPersonEntity> implements OrgUnitPostPersonService {

    @Autowired
    public OrgUnitPostPersonServiceImp(OrgUnitPostPersonRepository repository) {
        super(repository, "OrgUnitPostPersonEntity");
    }
}
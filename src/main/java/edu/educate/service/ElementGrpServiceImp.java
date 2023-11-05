package edu.educate.service;

import edu.educate.model.ElementGrpEntity;
import edu.educate.repository.ElementGrpRepository;
import edu.educate.service.baseService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("elementGrpService")
public class ElementGrpServiceImp extends GenericServiceImpl<ElementGrpEntity> implements ElementGrpService {

    @Autowired
    public ElementGrpServiceImp(ElementGrpRepository repository) {
        super(repository, "ElementGrpEntity");
    }
}

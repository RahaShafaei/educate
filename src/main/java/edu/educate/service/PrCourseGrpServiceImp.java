package edu.educate.service;

import edu.educate.model.PrCourseGrpEntity;
import edu.educate.repository.PrCourseGrpRepository;
import edu.educate.service.baseService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("prCourseGrpService")
public class PrCourseGrpServiceImp extends GenericServiceImpl<PrCourseGrpEntity> implements PrCourseGrpService {

    @Autowired
    public PrCourseGrpServiceImp(PrCourseGrpRepository repository) {
        super(repository, "PrCourseGrpEntity");
    }
}
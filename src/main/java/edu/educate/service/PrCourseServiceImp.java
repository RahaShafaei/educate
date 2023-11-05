package edu.educate.service;

import edu.educate.model.PrCourseEntity;
import edu.educate.repository.PrCourseRepository;
import edu.educate.service.baseService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("prCourseService")
public class PrCourseServiceImp extends GenericServiceImpl<PrCourseEntity> implements PrCourseService {

    @Autowired
    public PrCourseServiceImp(PrCourseRepository repository) {
        super(repository, "PrCourseEntity");
    }
}
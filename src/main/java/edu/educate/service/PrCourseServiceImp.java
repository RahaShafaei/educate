package edu.educate.service;

import edu.educate.dto.PrCourseDto;
import edu.educate.model.PrCourseEntity;
import edu.educate.model.baseModel.BaseEntity;
import edu.educate.repository.PrCourseRepository;
import edu.educate.service.baseService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("prCourseService")
public class PrCourseServiceImp extends GenericServiceImpl<PrCourseEntity, PrCourseDto> implements PrCourseService {

    @Autowired
    public PrCourseServiceImp(PrCourseRepository repository) {
        super(repository, "PrCourseEntity");
    }

}
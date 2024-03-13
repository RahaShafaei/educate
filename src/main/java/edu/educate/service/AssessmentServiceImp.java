package edu.educate.service;

import edu.educate.dto.AssessmentDto;
import edu.educate.model.AssessmentEntity;
import edu.educate.repository.AssessmentRepository;
import edu.educate.service.baseService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AssessmentService")
public class AssessmentServiceImp extends GenericServiceImpl<AssessmentEntity, AssessmentDto> implements AssessmentService {

    @Autowired
    public AssessmentServiceImp(AssessmentRepository repository) {
        super(repository, "AssessmentEntity");
    }
}

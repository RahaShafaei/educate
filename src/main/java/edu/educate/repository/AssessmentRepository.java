package edu.educate.repository;

import edu.educate.model.AssessmentEntity;
import edu.educate.repository.baseRepository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentRepository extends GenericRepository<AssessmentEntity> {
}

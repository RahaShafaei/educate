package edu.educate.repository;

import edu.educate.model.PlanProcessEntity;
import edu.educate.repository.baseRepository.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanProcessRepository extends GenericRepository<PlanProcessEntity> {
    List<PlanProcessEntity> findByDeletedFalseAndPlanId(Integer id);
}

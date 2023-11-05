package edu.educate.repository;

import edu.educate.model.PlansEntity;
import edu.educate.repository.baseRepository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlansRepository extends GenericRepository<PlansEntity> {
}
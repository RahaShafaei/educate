package edu.educate.repository;

import edu.educate.model.ProcessEntity;
import edu.educate.repository.baseRepository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessRepository extends GenericRepository<ProcessEntity> {
}

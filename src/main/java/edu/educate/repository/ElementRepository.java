package edu.educate.repository;

import edu.educate.model.ElementEntity;
import edu.educate.repository.baseRepository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElementRepository extends GenericRepository<ElementEntity> {
}

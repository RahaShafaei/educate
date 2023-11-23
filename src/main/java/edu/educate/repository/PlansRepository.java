package edu.educate.repository;

import edu.educate.model.ElementEntity;
import edu.educate.model.PlansEntity;
import edu.educate.repository.baseRepository.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlansRepository extends GenericRepository<PlansEntity> {
    List<PlansEntity> findByElementStatusNotIn(List<ElementEntity> elementEntities);
}
package edu.educate.repository;

import edu.educate.model.OrgUnitEntity;
import edu.educate.repository.baseRepository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgUnitRepository extends GenericRepository<OrgUnitEntity> {
}
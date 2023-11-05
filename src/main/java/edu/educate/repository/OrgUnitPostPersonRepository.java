package edu.educate.repository;

import edu.educate.model.OrgUnitPostPersonEntity;
import edu.educate.repository.baseRepository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgUnitPostPersonRepository  extends GenericRepository<OrgUnitPostPersonEntity> {
}
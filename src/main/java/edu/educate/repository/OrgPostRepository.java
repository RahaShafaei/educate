package edu.educate.repository;

import edu.educate.model.OrgPostEntity;
import edu.educate.repository.baseRepository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgPostRepository extends GenericRepository<OrgPostEntity> {
}

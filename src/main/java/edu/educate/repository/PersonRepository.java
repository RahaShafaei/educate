package edu.educate.repository;

import edu.educate.model.PersonEntity;
import edu.educate.repository.baseRepository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends GenericRepository<PersonEntity> {
}
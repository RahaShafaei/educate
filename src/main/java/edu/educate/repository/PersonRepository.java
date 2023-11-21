package edu.educate.repository;

import edu.educate.model.PersonEntity;
import edu.educate.repository.baseRepository.GenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends GenericRepository<PersonEntity> {
}
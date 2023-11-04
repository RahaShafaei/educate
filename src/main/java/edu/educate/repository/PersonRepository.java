package edu.educate.repository;

import edu.educate.model.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {
    List<PersonEntity> findByDeletedFalse();
    Optional<PersonEntity> findByIdAndDeletedFalse(Integer id);
}

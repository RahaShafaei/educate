package edu.educate.repository;

import edu.educate.model.ElementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ElementRepository extends JpaRepository<ElementEntity, Integer> {
    List<ElementEntity> findByDeletedFalse();
    Optional<ElementEntity> findByIdAndDeletedFalse(Integer id);
}

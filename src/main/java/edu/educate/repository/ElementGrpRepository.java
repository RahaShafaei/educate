package edu.educate.repository;

import edu.educate.model.ElementGrpEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ElementGrpRepository extends JpaRepository<ElementGrpEntity, Integer> {
    List<ElementGrpEntity> findByDeletedFalse();
    Optional<ElementGrpEntity> findByIdAndDeletedFalse(Integer id);
}

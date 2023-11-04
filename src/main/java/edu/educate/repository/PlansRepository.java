package edu.educate.repository;

import edu.educate.model.PlansEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlansRepository extends JpaRepository<PlansEntity, Integer> {
    List<PlansEntity> findByDeletedFalse();
    Optional<PlansEntity> findByIdAndDeletedFalse(Integer id);
}

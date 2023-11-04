package edu.educate.repository;

import edu.educate.model.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RolesRepository extends JpaRepository<RolesEntity, Integer> {
    List<RolesEntity> findByDeletedFalse();
    Optional<RolesEntity> findByIdAndDeletedFalse(Integer id);
}

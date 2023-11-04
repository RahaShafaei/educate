package edu.educate.repository;

import edu.educate.model.OrgUnitEntity;
import edu.educate.model.OrgUnitPostPersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrgUnitRepository extends JpaRepository<OrgUnitEntity, Integer> {
    List<OrgUnitEntity> findByDeletedFalse();
    Optional<OrgUnitEntity> findByIdAndDeletedFalse(Integer id);
}

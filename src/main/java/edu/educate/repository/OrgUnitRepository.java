package edu.educate.repository;

import edu.educate.model.OrgUnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrgUnitRepository extends JpaRepository<OrgUnitEntity, Integer> {
}

package edu.educate.repository;

import edu.educate.model.OrgUnitPostPersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrgUnitPostPersonRepository  extends JpaRepository<OrgUnitPostPersonEntity, Integer> {
}

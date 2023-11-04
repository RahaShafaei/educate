package edu.educate.repository;

import edu.educate.model.OrgPostEntity;
import edu.educate.model.OrgUnitPostPersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrgUnitPostPersonRepository  extends JpaRepository<OrgUnitPostPersonEntity, Integer> {
    List<OrgUnitPostPersonEntity> findByDeletedFalse();
    Optional<OrgUnitPostPersonEntity> findByIdAndDeletedFalse(Integer id);
}

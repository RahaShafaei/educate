package edu.educate.repository;

import edu.educate.model.OrgUnitEntity;
import edu.educate.repository.baseRepository.GenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrgUnitRepository extends GenericRepository<OrgUnitEntity> {
    List<OrgUnitEntity> findByParentOrgUnitIsNull();
    List<OrgUnitEntity> findByParentOrgUnitId(Integer id);
    List<OrgUnitEntity> findByElementTypeId(Integer id);
    Page<OrgUnitEntity> findByDeletedFalseAndTitleNotOrderByIdDesc(Pageable pageable, String title);
}
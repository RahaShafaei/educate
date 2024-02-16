package edu.educate.repository;

import edu.educate.model.OrgUnitEntity;
import edu.educate.model.PrCourseEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import edu.educate.repository.baseRepository.GenericRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrgUnitRepository extends GenericRepository<OrgUnitEntity> {
    Page<OrgUnitEntity> findAll(Specification<OrgUnitEntity> dateSpec, Pageable pageable);

    List<OrgUnitEntity> findAll(Specification<OrgUnitEntity> dateSpec);

    List<OrgUnitEntity> findByParentOrgUnitIsNull();
    List<OrgUnitEntity> findByParentOrgUnitId(Integer id);

//    @Query("SELECT p FROM OrgUnitEntity p LEFT JOIN FETCH p.parentOrgUnit UNION ")
//    Page<OrgUnitEntity> findAllWithChildren(Specification<OrgUnitEntity> dateSpec, Pageable pageable);

    @Query(value = "select *\n" +
            "FROM org_unit\n" +
            "where id in (select id from org_unit where parent_org_unit_id is not null)\n" +
            "or\n" +
            "id in (select id from org_unit where parent_org_unit_id is null)", nativeQuery = true)
    Page<OrgUnitEntity> findAllWithChildren(Example example, Pageable pageable);
}
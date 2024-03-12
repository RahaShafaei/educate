package edu.educate.service;

import edu.educate.dto.OrgUnitDto;
import edu.educate.model.OrgUnitEntity;
import edu.educate.model.PlansEntity;
import edu.educate.model.PrCourseEntity;
import edu.educate.repository.OrgUnitRepository;
import edu.educate.repository.PlansRepository;
import edu.educate.repository.PrCourseRepository;
import edu.educate.service.baseService.GenericServiceImpl;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("orgUnitService")
public class OrgUnitServiceImp extends GenericServiceImpl<OrgUnitEntity, OrgUnitDto> implements OrgUnitService {

    @Autowired
    public OrgUnitServiceImp(OrgUnitRepository repository) {
        super(repository, "OrgUnitEntity");
    }

    @Override
    public List<OrgUnitEntity> findByParentOrgUnitIsNull() {
        return ((OrgUnitRepository) repository).findByParentOrgUnitIsNull();
    }

    @Override
    public List<OrgUnitEntity> findByParentOrgUnitId(Integer id) {
        return ((OrgUnitRepository) repository).findByParentOrgUnitId(id);
    }

    @Override
    public List<OrgUnitEntity> findByElementTypeId() {
        return ((OrgUnitRepository) repository).findByElementTypeId(17);
    }

    @Override
    public Page<OrgUnitEntity> getAllEntities(Pageable pageable) {
        return ((OrgUnitRepository) repository).findByDeletedFalseAndTitleNotOrderByIdDesc(pageable, "root");
    }

    //    @Override
//    public Page<OrgUnitEntity> getAllEntities(Example<OrgUnitEntity> example, Pageable pageable) {
//        Specification<OrgUnitEntity> dateSpec = getCriterias(example);
//        return ((OrgUnitRepository) repository).findAllWithChildren(example, pageable);
////        return ((OrgUnitRepository) repository).findAllWithChildren(dateSpec, pageable);
//    }

//    @Override
//    public Page<OrgUnitEntity> getAllEntities(Example<OrgUnitEntity> example, Pageable pageable) {
//
//        Specification<OrgUnitEntity> dateSpec = getCriterias(example);
//
//        return ((OrgUnitRepository) repository).findAll(dateSpec, pageable);
//    }

//    @Override
//    public List<OrgUnitEntity> findEntities(Example<OrgUnitEntity> example) {
//        Specification<OrgUnitEntity> dateSpec = getCriterias(example);
//
//        return ((OrgUnitRepository) repository).findAll(dateSpec);
//    }

    private Specification<OrgUnitEntity> getCriterias(Example<OrgUnitEntity> example) {
        Specification<OrgUnitEntity> dateSpec = (root, query, criteriaBuilder) -> {

            Join<OrgUnitEntity, OrgUnitEntity> parentJoin = root.join("parentOrgUnit");
            Join<OrgUnitEntity, OrgUnitEntity> childJoin = root.join("orgUnits");
            List<Predicate> predicates = new ArrayList<>();

            Predicate parentTitle;
            Predicate childTitle;
            Predicate titleCondition;

            Predicate parentCode;
            Predicate childCode;
            Predicate codeCondition;

            Predicate parentType;
            Predicate childType;
            Predicate typeCondition;

            if (example.getProbe().getParentOrgUnit().getTitle() != null) {
//                predicates.add(criteriaBuilder.like(parentJoin.get("title"), "%" + example.getProbe().getParentOrgUnit().getTitle() + "%"));
                predicates.add(criteriaBuilder.like(root.get("parentOrgUnit").get("title"), "%" + example.getProbe().getParentOrgUnit().getTitle() + "%"));
            }
            if (example.getProbe().getTitle() != null) {
//                parentTitle = criteriaBuilder.like(parentJoin.get("title"), "%" + example.getProbe().getTitle() + "%");
                parentTitle = criteriaBuilder.like(root.get("parentOrgUnit").get("title"), "%" + example.getProbe().getTitle() + "%");
//                childTitle = criteriaBuilder.like(root.get("title"), "%" + example.getProbe().getTitle() + "%");
//                titleCondition = criteriaBuilder.or(parentTitle, childTitle);
//                predicates.add(titleCondition);
                predicates.add(parentTitle);
            }
            if (example.getProbe().getCode() != null) {
//                parentCode = criteriaBuilder.like(parentJoin.get("code"), "%" + example.getProbe().getCode() + "%");
                parentCode = criteriaBuilder.like(root.get("parentOrgUnit").get("code"), "%" + example.getProbe().getCode() + "%");
//                childCode = criteriaBuilder.like(root.get("code"), "%" + example.getProbe().getCode() + "%");
//                codeCondition = criteriaBuilder.or(parentCode, childCode);
//                predicates.add(codeCondition);
                predicates.add(parentCode);
            }
            if (example.getProbe().getElementType().getPrTitle() != null) {
//                parentType = criteriaBuilder.like(parentJoin.get("elementType").get("prTitle"), "%" + example.getProbe().getElementType().getPrTitle() + "%");
                parentType = criteriaBuilder.like(root.get("parentOrgUnit").get("elementType").get("prTitle"), "%" + example.getProbe().getElementType().getPrTitle() + "%");
//                childType = criteriaBuilder.like(root.get("elementType").get("prTitle"), "%" + example.getProbe().getElementType().getPrTitle() + "%");
//                typeCondition = criteriaBuilder.or(parentType, childType);
//                predicates.add(typeCondition);
                predicates.add(parentType);
            }

            predicates.add(criteriaBuilder.equal(root.get("deleted"), example.getProbe().isDeleted()));

            if (predicates.isEmpty()) {
                predicates.add(criteriaBuilder.greaterThan(root.get("id"), 1));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return dateSpec;
    }
}
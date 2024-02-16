package edu.educate.repository;

import edu.educate.model.ElementEntity;
import edu.educate.model.PlansEntity;
import edu.educate.repository.baseRepository.GenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlansRepository extends GenericRepository<PlansEntity> {
    List<PlansEntity> findByElementStatusNotIn(List<ElementEntity> elementEntities);

    Page<PlansEntity> findAll(Specification<PlansEntity> dateSpec, Pageable pageable);

    List<PlansEntity> findAll(Specification<PlansEntity> dateSpec);
}

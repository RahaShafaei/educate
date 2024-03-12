package edu.educate.repository;

import edu.educate.model.ElementEntity;
import edu.educate.repository.baseRepository.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElementRepository extends GenericRepository<ElementEntity> {
    List<ElementEntity> findByLtTitleNotAndElementGrpId(String title, Integer grpId);
}

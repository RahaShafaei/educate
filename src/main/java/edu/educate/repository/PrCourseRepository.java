package edu.educate.repository;

import edu.educate.model.PrCourseEntity;
import edu.educate.repository.baseRepository.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrCourseRepository extends GenericRepository<PrCourseEntity> {
    List<PrCourseEntity> findByPrCourseGrpId(Integer id);
}
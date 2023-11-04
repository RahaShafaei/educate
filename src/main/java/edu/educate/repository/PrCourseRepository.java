package edu.educate.repository;

import edu.educate.model.PrCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrCourseRepository extends JpaRepository<PrCourseEntity, Integer> {
    List<PrCourseEntity> findByDeletedFalse();
    Optional<PrCourseEntity> findByIdAndDeletedFalse(Integer id);
}

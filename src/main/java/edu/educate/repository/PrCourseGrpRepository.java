package edu.educate.repository;

import edu.educate.model.PrCourseGrpEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrCourseGrpRepository  extends JpaRepository<PrCourseGrpEntity, Integer> {
    List<PrCourseGrpEntity> findByDeletedFalse();
    Optional<PrCourseGrpEntity> findByIdAndDeletedFalse(Integer id);
}

package edu.educate.repository;

import edu.educate.model.PrCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrCourseRepository extends JpaRepository<PrCourseEntity, Integer> {
}

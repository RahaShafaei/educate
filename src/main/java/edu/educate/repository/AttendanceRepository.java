package edu.educate.repository;

import edu.educate.model.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Integer> {
    List<AttendanceEntity> findByDeletedFalse();
    Optional<AttendanceEntity> findByIdAndDeletedFalse(Integer id);
}

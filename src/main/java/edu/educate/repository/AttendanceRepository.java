package edu.educate.repository;

import edu.educate.model.AttendanceEntity;
import edu.educate.repository.baseRepository.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends GenericRepository<AttendanceEntity> {
    List<AttendanceEntity> findByPlanId(Integer id);
}

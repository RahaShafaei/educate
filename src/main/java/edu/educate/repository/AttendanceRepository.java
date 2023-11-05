package edu.educate.repository;

import edu.educate.model.AttendanceEntity;
import edu.educate.repository.baseRepository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends GenericRepository<AttendanceEntity> {
}

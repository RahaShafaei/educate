package edu.educate.repository;

import edu.educate.model.MeetingEntity;
import edu.educate.repository.baseRepository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository extends GenericRepository<MeetingEntity> {
}

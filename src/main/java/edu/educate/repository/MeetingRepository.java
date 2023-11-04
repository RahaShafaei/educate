package edu.educate.repository;

import edu.educate.model.MeetingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MeetingRepository extends JpaRepository<MeetingEntity, Integer> {
    List<MeetingEntity> findByDeletedFalse();
    Optional<MeetingEntity> findByIdAndDeletedFalse(Integer id);
}

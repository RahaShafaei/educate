package edu.educate.repository;

import edu.educate.model.MeetingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<MeetingEntity, Integer> {
}

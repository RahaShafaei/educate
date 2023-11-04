package edu.educate.repository;

import edu.educate.model.OrgPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrgPostRepository extends JpaRepository<OrgPostEntity, Integer> {
    List<OrgPostEntity> findByDeletedFalse();
    Optional<OrgPostEntity> findByIdAndDeletedFalse(Integer id);
}

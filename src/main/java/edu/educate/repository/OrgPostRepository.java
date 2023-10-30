package edu.educate.repository;

import edu.educate.model.OrgPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrgPostRepository extends JpaRepository<OrgPostEntity, Integer> {
}

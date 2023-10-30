package edu.educate.repository;

import edu.educate.model.PlansEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlansRepository extends JpaRepository<PlansEntity, Integer> {
}

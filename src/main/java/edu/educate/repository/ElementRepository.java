package edu.educate.repository;

import edu.educate.model.ElementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElementRepository extends JpaRepository<ElementEntity, Integer> {
}

package edu.educate.repository;

import edu.educate.model.LocationEntity;
import edu.educate.repository.baseRepository.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends GenericRepository<LocationEntity> {
    List<LocationEntity> findByOrgUnitsId(Integer id);
}

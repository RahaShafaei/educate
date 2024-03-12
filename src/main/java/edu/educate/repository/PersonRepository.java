package edu.educate.repository;

import edu.educate.model.PersonEntity;
import edu.educate.repository.baseRepository.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends GenericRepository<PersonEntity> {

    @Query("SELECT p FROM PersonEntity p JOIN p.orgUnitPostPersons oupp WHERE oupp.orgUnit.id = :orgUnitId AND oupp.ltToDate IS NULL")
    List<PersonEntity> findByOrgUnitIdAndLtToDateIsNull(@Param("orgUnitId") Integer orgUnitId);
}
package edu.educate.repository;

import edu.educate.model.PersonEntity;
import edu.educate.repository.baseRepository.GenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends GenericRepository<PersonEntity> {
//    @Query("SELECT p FROM PersonEntity p WHERE " +
//            "(:fname is null or p.fname LIKE :fname) AND " +
//            "(:lname is null or p.lname LIKE :lname) AND " +
//            "(:fatherName is null or p.fatherName LIKE :fatherName) AND " +
//            "(:nlCode is null or p.nlCode LIKE :nlCode) AND " +
//            "(:prCode is null or p.prCode LIKE :prCode) AND " +
//            "(:tel is null or p.tel LIKE :tel)")
//    Page<PersonEntity> searchByAllFields(
//            @Param("fname") String fname,
//            @Param("lname") String lname,
//            @Param("fatherName") String fatherName,
//            @Param("nlCode") String nlCode,
//            @Param("prCode") String prCode,
//            @Param("tel") String tel,
//            Pageable pageable
//    );

    @Query(value = "SELECT p.* FROM educate.dbo.person p WHERE " +
            "p.fname LIKE %:fname% COLLATE Persian_100_CI_AI AND " +
            "p.lname LIKE %:lname% COLLATE Persian_100_CI_AI AND " +
            "p.father_name LIKE %:fatherName% COLLATE Persian_100_CI_AI AND " +
            "p.nl_code LIKE %:nlCode% AND " +
            "p.pr_code LIKE %:prCode% AND " +
            "p.tel LIKE %:tel% ",
            nativeQuery = true)
    Page<PersonEntity> searchByAllFields(
            @Param("fname") String fname,
            @Param("lname") String lname,
            @Param("fatherName") String fatherName,
            @Param("nlCode") String nlCode,
            @Param("prCode") String prCode,
            @Param("tel") String tel,
            Pageable pageable
    );

}
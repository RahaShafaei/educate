package edu.educate.service;

import edu.educate.model.PersonEntity;
import edu.educate.service.baseService.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonService extends GenericService<PersonEntity> {
    Page<PersonEntity> searchByAllFields(PersonEntity person, Pageable pageable);
}

package edu.educate.service;

import edu.educate.dto.PersonDto;
import edu.educate.model.PersonEntity;
import edu.educate.service.baseService.GenericService;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonService extends GenericService<PersonEntity, PersonDto> {
    List<PersonEntity> findAllByOrgUnitId(Integer planId);

}

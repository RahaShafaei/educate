package edu.educate.service;

import edu.educate.model.PersonEntity;
import edu.educate.repository.PersonRepository;
import edu.educate.service.baseService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("personService")
public class PersonServiceImp extends GenericServiceImpl<PersonEntity> implements PersonService {

    @Autowired
    public PersonServiceImp(PersonRepository repository) {
        super(repository, "PersonEntity");
    }

}
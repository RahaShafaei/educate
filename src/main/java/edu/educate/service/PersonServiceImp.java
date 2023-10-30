package edu.educate.service;

import edu.educate.dto.PersonDto;
import edu.educate.model.PersonEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("personService")
public class PersonServiceImp implements PersonService{
    @Override
    public List<PersonDto> getPersons() {
        return null;
    }

    @Override
    public PersonDto getPerson(Integer id) {
        return null;
    }

    @Override
    public List<PersonDto> getPersonPersons(Integer id) {
        return null;
    }

    @Override
    public Boolean deletePerson(Integer id) {
        return null;
    }

    @Override
    public PersonDto createPerson(PersonEntity Person) {
        return null;
    }
}

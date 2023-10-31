package edu.educate.service;

import edu.educate.dto.PersonDto;
import edu.educate.model.PersonEntity;

import java.util.List;

public interface PersonService {
    public List<PersonDto> getPersons();

    public PersonDto getPerson(Integer id);

    public Boolean deletePerson(Integer id);

    public PersonDto createPerson(PersonEntity person);
}

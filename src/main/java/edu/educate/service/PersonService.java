package edu.educate.service;

import edu.educate.model.PersonEntity;

import java.util.List;

public interface PersonService {
    public List<PersonEntity> getPersons();

    public PersonEntity getPerson(Integer id);

    public Boolean deletePerson(Integer id);

    public PersonEntity createPerson(PersonEntity person);
}

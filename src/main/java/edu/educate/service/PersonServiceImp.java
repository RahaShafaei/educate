package edu.educate.service;

import edu.educate.exception.ItemNotFoundException;
import edu.educate.exception.ParametersNotValidException;
import edu.educate.model.PersonEntity;
import edu.educate.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service("personService")
public class PersonServiceImp implements PersonService{

    private static final String PERSON_ID = "Person id: ";
    private final PersonRepository personRepository;
    @Override
    public List<PersonEntity> getPersons() {
        return personRepository.findByDeletedFalse();
    }

    @Override
    public PersonEntity getPerson(Integer id) {
        Optional<PersonEntity> person = personRepository.findByIdAndDeletedFalse(id);

        if (person.isEmpty())
            throw new ItemNotFoundException(PERSON_ID + id);

        return person.get();
    }

    @Override
    public Boolean deletePerson(Integer id) {
        Optional<PersonEntity> person = personRepository.findByIdAndDeletedFalse(id);

        if (!person.isEmpty()) {
            person.get().setDeleted(true);
            person.get().setDeletedAt(LocalDateTime.now());
            personRepository.save(person.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public PersonEntity createPerson(PersonEntity person) {

        PersonEntity savedPerson = personRepository.save(person);

        return savedPerson;
    }
}

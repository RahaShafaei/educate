package edu.educate.service;

import edu.educate.dto.PersonDto;
import edu.educate.dto.PersonMapper;
import edu.educate.exception.ItemNotFoundException;
import edu.educate.exception.ParametersNotValidException;
import edu.educate.model.PersonEntity;
import edu.educate.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service("personService")
public class PersonServiceImp implements PersonService{

    private static final String PERSON_ID = "Person id: ";
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    @Override
    public List<PersonDto> getPersons() {
        return personRepository.findAll()
                .stream()
                .map(personMapper::toDto)
                .toList();
    }

    @Override
    public PersonDto getPerson(Integer id) {
        Optional<PersonEntity> person = personRepository.findById(id);

        if (person.isEmpty())
            throw new ItemNotFoundException(PERSON_ID + id);

        return personMapper.toDto(person.get());
    }

    @Override
    public Boolean deletePerson(Integer id) {
        Optional<PersonEntity> person = personRepository.findById(id);

        if (!person.isEmpty()) {
            personRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public PersonDto createPerson(PersonEntity person) {
        if (person.getFname() == null || person.getFname().isEmpty())
            throw new ParametersNotValidException("Fname of Person should not be empty.");

        if (person.getLname() == null || person.getLname().isEmpty())
            throw new ParametersNotValidException("Lname of Person should not be empty.");

        if (person.getPrCode() == null || person.getPrCode().isEmpty())
            throw new ParametersNotValidException("PrCode of Person should not be empty.");

        PersonEntity savedPerson = personRepository.save(person);

        return personMapper.toDto(savedPerson);
    }
}

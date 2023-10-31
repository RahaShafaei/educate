package edu.educate.service;

import edu.educate.model.PersonEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
@Transactional
public class PersonServiceTest {

    @Autowired
    private PersonService personService;
    @Test
    public void testSaveDeletePerson() {

        PersonEntity person = new PersonEntity();

        person.setFname("Sample Fname : fname");
        person.setLname("Sample Lname : lname");
        person.setPrCode("Sample PrCode : prCode");

        PersonEntity savedPerson = personService.createPerson(person);

        assertNotNull(savedPerson.getId());

        PersonEntity retrievedPerson = personService.getPerson(savedPerson.getId());

        assertNotNull(retrievedPerson);
        assertEquals(savedPerson.getFname(), retrievedPerson.getFname());
        assertEquals(savedPerson.getLname(), retrievedPerson.getLname());
        assertEquals(savedPerson.getPrCode(), retrievedPerson.getPrCode());

        Boolean personIsDeleted = personService.deletePerson(savedPerson.getId());

        assertEquals(true, personIsDeleted);
    }
}

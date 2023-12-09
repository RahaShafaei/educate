package edu.educate.service;

import edu.educate.model.OrgUnitPostPersonEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
@Transactional
public class OrgUnitPostPersonServiceTest {
    @Autowired
    private OrgUnitPostPersonService unitPostPersonService;

    @Autowired
    private OrgUnitService orgUnitService;

    @Autowired
    private OrgPostService orgPostService;

    @Autowired
    private PersonService PersonService;

    @Autowired
    private ElementService elementService;
    @Test
    public void testSaveDeleteOrgUnitPostPerson() {

        OrgUnitPostPersonEntity orgUnitPostPerson = new OrgUnitPostPersonEntity();

        orgUnitPostPerson.setOrgUnit(orgUnitService.getEntity(1));
        orgUnitPostPerson.setOrgPost(orgPostService.getEntity(1));
        orgUnitPostPerson.setPerson(PersonService.getEntity(8));
        orgUnitPostPerson.setFromDate(
                LocalDateTime.parse("2023-10-26 15:01:10",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                )
        );
        orgUnitPostPerson.setToDate(
                LocalDateTime.parse("2023-10-27 15:01:10",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                )
        );

        OrgUnitPostPersonEntity savedOrgUnitPostPerson = unitPostPersonService.createEntity(orgUnitPostPerson);

        assertNotNull(savedOrgUnitPostPerson.getId());

        OrgUnitPostPersonEntity retrievedOrgUnitPostPerson = unitPostPersonService.getEntity(savedOrgUnitPostPerson.getId());

        assertNotNull(retrievedOrgUnitPostPerson);
        assertEquals(savedOrgUnitPostPerson.getOrgUnit(), retrievedOrgUnitPostPerson.getOrgUnit());
        assertEquals(savedOrgUnitPostPerson.getOrgPost(), retrievedOrgUnitPostPerson.getOrgPost());
        assertEquals(savedOrgUnitPostPerson.getPerson(), retrievedOrgUnitPostPerson.getPerson());

        Boolean orgUnitPostPersonIsDeleted = unitPostPersonService.deleteEntity(savedOrgUnitPostPerson.getId());

        assertEquals(true, orgUnitPostPersonIsDeleted);
    }
}

package edu.educate.service;

import edu.educate.model.OrgUnitPostPersonEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

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

        orgUnitPostPerson.setOrgUnit(orgUnitService.getOrgUnit(1));
        orgUnitPostPerson.setOrgPost(orgPostService.getOrgPost(1));
        orgUnitPostPerson.setPerson(PersonService.getPerson(1));
        orgUnitPostPerson.setFromDate(Date.valueOf("2023-10-26"));
        orgUnitPostPerson.setToDate(Date.valueOf("2023-10-26"));

        OrgUnitPostPersonEntity savedOrgUnitPostPerson = unitPostPersonService.createOrgUnitPostPerson(orgUnitPostPerson);

        assertNotNull(savedOrgUnitPostPerson.getId());

        OrgUnitPostPersonEntity retrievedOrgUnitPostPerson = unitPostPersonService.getOrgUnitPostPerson(savedOrgUnitPostPerson.getId());

        assertNotNull(retrievedOrgUnitPostPerson);
        assertEquals(savedOrgUnitPostPerson.getOrgUnit(), retrievedOrgUnitPostPerson.getOrgUnit());
        assertEquals(savedOrgUnitPostPerson.getOrgPost(), retrievedOrgUnitPostPerson.getOrgPost());
        assertEquals(savedOrgUnitPostPerson.getPerson(), retrievedOrgUnitPostPerson.getPerson());

        Boolean orgUnitPostPersonIsDeleted = unitPostPersonService.deleteOrgUnitPostPerson(savedOrgUnitPostPerson.getId());

        assertEquals(true, orgUnitPostPersonIsDeleted);
    }
}

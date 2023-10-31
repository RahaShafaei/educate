package edu.educate.service;

import edu.educate.model.OrgUnitEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
@Transactional
public class OrgUnitServiceTest {

    @Autowired
    private OrgUnitService orgUnitService;
    @Test
    public void testSaveDeleteOrgUnit() {

        OrgUnitEntity orgUnit = new OrgUnitEntity();

        orgUnit.setTitle("Sample orgUnit : Title");

        OrgUnitEntity savedOrgUnit = orgUnitService.createOrgUnit(orgUnit);

        assertNotNull(savedOrgUnit.getId());

        OrgUnitEntity retrievedOrgUnit = orgUnitService.getOrgUnit(savedOrgUnit.getId());

        assertNotNull(retrievedOrgUnit);
        assertEquals(savedOrgUnit.getTitle(), retrievedOrgUnit.getTitle());

        Boolean orgUnitIsDeleted = orgUnitService.deleteOrgUnit(savedOrgUnit.getId());

        assertEquals(true, orgUnitIsDeleted);
    }
    
}

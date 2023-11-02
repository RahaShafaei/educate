package edu.educate.service;

import edu.educate.model.PlansEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
@Transactional
public class PlansServiceTest {

    @Autowired
    private PlansService plansService;

    @Autowired
    private OrgUnitService orgUnitService;

    @Autowired
    private PrCourseService prCourseService;

    @Autowired
    private OrgUnitPostPersonService orgUnitPostPersonService;

    @Autowired
    private ElementService elementService;
    @Test
    public void testSaveDeletePlans() {

        PlansEntity plans = new PlansEntity();

        plans.setOrgUnit(orgUnitService.getOrgUnit(172));
        plans.setPrCourse(prCourseService.getPrCourse(4));
        plans.setOrgUnitPostPerson(orgUnitPostPersonService.getOrgUnitPostPerson(35));
        plans.setElementStatus(elementService.getElement(4));
        plans.setElementType(elementService.getElement(9));
        plans.setTitle("Sample Plan : Title Test");
        plans.setFromDate(Date.valueOf("2023-10-26"));
        plans.setToDate(Date.valueOf("2023-10-27"));

        PlansEntity savedPlan = plansService.createPlan(plans);

        assertNotNull(savedPlan.getId());

        PlansEntity retrievedPlan = plansService.getPlan(savedPlan.getId());

        assertNotNull(retrievedPlan);
        assertEquals(savedPlan.getTitle(), retrievedPlan.getTitle());

        Boolean planIsDeleted = plansService.deletePlan(savedPlan.getId());

        assertEquals(true, planIsDeleted);
    }
}

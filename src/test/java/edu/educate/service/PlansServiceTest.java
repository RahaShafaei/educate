package edu.educate.service;

import edu.educate.model.PlansEntity;
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
public class PlansServiceTest {

    @Autowired
    private PlansService plansService;

    @Autowired
    private OrgUnitService orgUnitService;

    @Autowired
    private PrCourseService prCourseService;

    @Autowired
    private PersonService personService;

    @Autowired
    private ElementService elementService;
    @Test
    public void testSaveDeletePlans() {

        PlansEntity plans = new PlansEntity();

        plans.setOrgUnit(orgUnitService.getEntity(172));
        plans.setPrCourse(prCourseService.getEntity(4));
        plans.setPerson(personService.getEntity(8));
        plans.setElementStatus(elementService.getEntity(4));
        plans.setElementType(elementService.getEntity(9));
        plans.setTitle("Sample Plan : Title Test");
        plans.setFromDate(
                LocalDateTime.parse("2023-10-26 15:01:10",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                )
        );
        plans.setToDate(
                LocalDateTime.parse("2023-10-27 15:01:10",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                )
        );

        PlansEntity savedPlan = plansService.createEntity(plans);

        assertNotNull(savedPlan.getId());

        PlansEntity retrievedPlan = plansService.getEntity(savedPlan.getId());

        assertNotNull(retrievedPlan);
        assertEquals(savedPlan.getTitle(), retrievedPlan.getTitle());

        Boolean planIsDeleted = plansService.deleteEntity(savedPlan.getId());

        assertEquals(true, planIsDeleted);
    }
}

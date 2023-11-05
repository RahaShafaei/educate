package edu.educate.service;

import edu.educate.model.PrCourseEntity;
import edu.educate.model.PrCourseGrpEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
@Transactional
public class PrCourseGrpServiceTest {

    @Autowired
    private PrCourseGrpService prCourseGrpService;

    @Test
    public void testSaveDeleteCourseGrp() {

        PrCourseGrpEntity prCourseGrp = new PrCourseGrpEntity();

        prCourseGrp.setLtTitle("Sample prCourseGrp : LtTitle");
        prCourseGrp.setPrTitle("Sample prCourseGrp : PrTitle");
        prCourseGrp.setDescr("This is a test prCourse.");

        PrCourseGrpEntity savedCourseGrp = prCourseGrpService.createEntity(prCourseGrp);

        assertNotNull(savedCourseGrp.getId());

        PrCourseGrpEntity retrievedCourseGrp = prCourseGrpService.getEntity(savedCourseGrp.getId());

        assertNotNull(retrievedCourseGrp);
        assertEquals(savedCourseGrp.getLtTitle(), retrievedCourseGrp.getLtTitle());
        assertEquals(savedCourseGrp.getPrTitle(), retrievedCourseGrp.getPrTitle());
        assertEquals(savedCourseGrp.getDescr(), retrievedCourseGrp.getDescr());

        Boolean courseGrpIsDeleted = prCourseGrpService.deleteEntity(savedCourseGrp.getId());

        assertEquals(true, courseGrpIsDeleted);
    }
}

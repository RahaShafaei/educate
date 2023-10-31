package edu.educate.service;

import edu.educate.model.PrCourseEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
@Transactional
public class PrCourseServiceTest {
    @Autowired
    private PrCourseService prCourseService;

    @Autowired
    PrCourseGrpService prCourseGrp;

    @Test
    public void testSaveDeleteCourse() {

        PrCourseEntity prCourse = new PrCourseEntity();

        prCourse.setPrCourseGrp(prCourseGrp.getPrCourseGrp(1));
        prCourse.setLtTitle("Sample prCourse : LtTitle");
        prCourse.setPrTitle("Sample prCourse : PrTitle");
        prCourse.setDescr("This is a test prCourse.");

        PrCourseEntity savedCourse = prCourseService.createPrCourse(prCourse);

        assertNotNull(savedCourse.getId());

        PrCourseEntity retrievedCourse = prCourseService.getPrCourse(savedCourse.getId());

        assertNotNull(retrievedCourse);
        assertEquals(savedCourse.getPrCourseGrp(), retrievedCourse.getPrCourseGrp());
        assertEquals(savedCourse.getLtTitle(), retrievedCourse.getLtTitle());
        assertEquals(savedCourse.getPrTitle(), retrievedCourse.getPrTitle());
        assertEquals(savedCourse.getDescr(), retrievedCourse.getDescr());

        Boolean courseIsDeleted = prCourseService.deletePrCourse(savedCourse.getId());

        assertEquals(true, courseIsDeleted);
    }
}

package edu.educate.service;

import edu.educate.model.PrCourseEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
@Transactional
public class PrCourseServiceTest {
    @Autowired
    private PrCourseService prCourseService;

    @Autowired
    PrCourseGrpService prCourseGrp;

    @Test
    public void testSaveCourse() {

        PrCourseEntity prCourse = new PrCourseEntity();

        prCourse.setPrCourseGrp(prCourseGrp.getPrCourseGrp(1));
        prCourse.setLtTitle("Sample Course : LtTitle");
        prCourse.setPrTitle("Sample Course : PrTitle");
        prCourse.setDescr("This is a test prCourse.");

        PrCourseEntity savedCourse = prCourseService.createPrCourse(prCourse);

        assertNotNull(savedCourse.getId());

//        Course retrievedCourse = prCourseService.findById(savedCourse.getId()).orElse(null);
//
//        assertNotNull(retrievedCourse);
//        assertEquals(savedCourse.getName(), retrievedCourse.getName());
//        assertEquals(savedCourse.getDescription(), retrievedCourse.getDescription());
    }
}

package edu.educate.service;

import edu.educate.model.AttendanceEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
@Transactional
public class AttendanceServiceTest {
    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private PlansService plansService;

    @Autowired
    private OrgUnitPostPersonService orgUnitPostPersonService;

    @Autowired
    private ElementService elementService;

    @Test
    public void testSaveDeleteAttendance() {

        AttendanceEntity attendance = new AttendanceEntity();

        attendance.setPlan(plansService.getPlan(2));
        attendance.setOrgUnitPostPerson(orgUnitPostPersonService.getOrgUnitPostPerson(1));
        attendance.setElement(elementService.getElement(1));
        attendance.setGrade(12.0F);

        AttendanceEntity savedAttendance = attendanceService.createAttendance(attendance);

        assertNotNull(savedAttendance.getId());

        AttendanceEntity retrievedAttendance = attendanceService.getAttendance(savedAttendance.getId());

        assertNotNull(retrievedAttendance);
        assertEquals(savedAttendance.getPlan(), retrievedAttendance.getPlan());
        assertEquals(savedAttendance.getOrgUnitPostPerson(), retrievedAttendance.getOrgUnitPostPerson());

        Boolean attendanceIsDeleted = attendanceService.deleteAttendance(savedAttendance.getId());

        assertEquals(true, attendanceIsDeleted);
    }

}

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

    @Test
    public void testSaveDeleteAttendance() {

        AttendanceEntity attendance = new AttendanceEntity();

        attendance.setPlan(plansService.getPlan(1));
        attendance.setOrgUnitPostPerson(orgUnitPostPersonService.getOrgUnitPostPerson(1));

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

package edu.educate.service;

import edu.educate.model.MeetingEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
@Transactional
public class MeetingServiceTest {
    @Autowired
    private MeetingService meetingService;

    @Autowired
    private PlansService plansService;

    @Test
    public void testSaveDeleteMeeting() {

        MeetingEntity meeting = new MeetingEntity();

        meeting.setTitle("Sample meeting : Title");
        meeting.setPlan(plansService.getPlan(1));

        MeetingEntity savedMeeting = meetingService.createMeeting(meeting);

        assertNotNull(savedMeeting.getId());

        MeetingEntity retrievedMeeting = meetingService.getMeeting(savedMeeting.getId());

        assertNotNull(retrievedMeeting);
        assertEquals(savedMeeting.getTitle(), retrievedMeeting.getTitle());

        Boolean meetingIsDeleted = meetingService.deleteMeeting(savedMeeting.getId());

        assertEquals(true, meetingIsDeleted);
    }
}

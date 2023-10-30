package edu.educate.service;

import edu.educate.dto.MeetingDto;
import edu.educate.dto.PersonDto;
import edu.educate.model.MeetingEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("meetingService")
public class MeetingServiceImp implements MeetingService{
    @Override
    public List<MeetingDto> getMeetings() {
        return null;
    }

    @Override
    public MeetingDto getMeeting(Integer id) {
        return null;
    }

    @Override
    public List<PersonDto> getMeetingPersons(Integer id) {
        return null;
    }

    @Override
    public Boolean deleteMeeting(Integer id) {
        return null;
    }

    @Override
    public MeetingDto createMeeting(MeetingEntity Meeting) {
        return null;
    }
}

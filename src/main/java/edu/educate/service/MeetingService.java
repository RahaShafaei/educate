package edu.educate.service;

import edu.educate.dto.MeetingDto;
import edu.educate.dto.PersonDto;
import edu.educate.model.MeetingEntity;

import java.util.List;

public interface MeetingService {
    public List<MeetingDto> getMeetings();

    public MeetingDto getMeeting(Integer id);

    public List<PersonDto> getMeetingPersons(Integer id);

    public Boolean deleteMeeting(Integer id);

    public MeetingDto createMeeting(MeetingEntity Meeting);
}

package edu.educate.service;

import edu.educate.model.MeetingEntity;

import java.util.List;

public interface MeetingService {
    public List<MeetingEntity> getMeetings();

    public MeetingEntity getMeeting(Integer id);

    public Boolean deleteMeeting(Integer id);

    public MeetingEntity createMeeting(MeetingEntity meeting);
}

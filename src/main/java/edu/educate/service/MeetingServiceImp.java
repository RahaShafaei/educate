package edu.educate.service;

import edu.educate.exception.ItemNotFoundException;
import edu.educate.exception.ParametersNotValidException;
import edu.educate.model.MeetingEntity;
import edu.educate.model.MeetingEntity;
import edu.educate.repository.MeetingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service("meetingService")
public class MeetingServiceImp implements MeetingService{

    private static final String MEETING_ID = "Meeting id: ";
    private final MeetingRepository meetingRepository;
    @Override
    public List<MeetingEntity> getMeetings() {
        return meetingRepository.findByDeletedFalse();
    }

    @Override
    public MeetingEntity getMeeting(Integer id) {
        Optional<MeetingEntity> meeting = meetingRepository.findByIdAndDeletedFalse(id);

        if (meeting.isEmpty())
            throw new ItemNotFoundException(MEETING_ID + id);

        return meeting.get();
    }

    @Override
    public Boolean deleteMeeting(Integer id) {
        Optional<MeetingEntity> meeting = meetingRepository.findByIdAndDeletedFalse(id);

        if (!meeting.isEmpty()) {
            meeting.get().setDeleted(true);
            meeting.get().setDeletedAt(LocalDateTime.now());
            meetingRepository.save(meeting.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public MeetingEntity createMeeting(MeetingEntity meeting) {

        MeetingEntity savedMeeting = meetingRepository.save(meeting);

        return savedMeeting;
    }
}

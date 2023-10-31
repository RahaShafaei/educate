package edu.educate.service;

import edu.educate.dto.MeetingMapper;
import edu.educate.dto.MeetingDto;
import edu.educate.exception.ItemNotFoundException;
import edu.educate.exception.ParametersNotValidException;
import edu.educate.model.MeetingEntity;
import edu.educate.model.MeetingEntity;
import edu.educate.repository.MeetingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service("meetingService")
public class MeetingServiceImp implements MeetingService{

    private static final String MEETING_ID = "Meeting id: ";
    private final MeetingRepository meetingRepository;
    private final MeetingMapper meetingMapper;
    @Override
    public List<MeetingDto> getMeetings() {
        return meetingRepository.findAll()
                .stream()
                .map(meetingMapper::toDto)
                .toList();
    }

    @Override
    public MeetingDto getMeeting(Integer id) {
        Optional<MeetingEntity> meeting = meetingRepository.findById(id);

        if (meeting.isEmpty())
            throw new ItemNotFoundException(MEETING_ID + id);

        return meetingMapper.toDto(meeting.get());
    }

    @Override
    public Boolean deleteMeeting(Integer id) {
        Optional<MeetingEntity> meeting = meetingRepository.findById(id);

        if (!meeting.isEmpty()) {
            meetingRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public MeetingDto createMeeting(MeetingEntity meeting) {
        if (meeting.getTitle() == null || meeting.getTitle().isEmpty())
            throw new ParametersNotValidException("Title of Meeting should not be empty.");

        if (meeting.getPlan() == null)
            throw new ParametersNotValidException("Plan of Meeting should not be empty.");

        MeetingEntity savedMeeting = meetingRepository.save(meeting);

        return meetingMapper.toDto(savedMeeting);
    }
}
